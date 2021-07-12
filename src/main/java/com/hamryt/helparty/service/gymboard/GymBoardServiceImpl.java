package com.hamryt.helparty.service.gymboard;

import com.hamryt.helparty.dto.board.gymboard.GymBoardDTO;
import com.hamryt.helparty.dto.board.gymboard.SimpleGymBoard;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.request.UpdateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardResponse;
import com.hamryt.helparty.exception.board.gymboard.GymBoardNotFoundException;
import com.hamryt.helparty.exception.board.gymboard.InsertGymBoardFailedException;
import com.hamryt.helparty.exception.board.gymboard.UpdateGymBoardFailedException;
import com.hamryt.helparty.exception.login.LoginUserDoesNotMatchException;
import com.hamryt.helparty.mapper.GymBoardMapper;
import com.hamryt.helparty.service.product.ProductService;
import com.hamryt.helparty.util.CacheNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymBoardServiceImpl implements GymBoardService {

    private final GymBoardMapper gymBoardMapper;
    private final ProductService productService;

    @Transactional
    @CacheEvict(cacheNames = CacheNames.GYMBOARD, allEntries = true)
    public void insertGymBoard(CreateGymBoardRequest createGymBoardRequest, Long loginId) {
        createGymBoardRequest.getUserType().validEqualUserType("GYM");

        SimpleGymBoard simpleGymBoard = SimpleGymBoard.of(createGymBoardRequest, loginId);

        if (gymBoardMapper.insertGymBoard(simpleGymBoard) != 1) {
            log.error("Insert GymBoard query failed : " + simpleGymBoard);
            throw new InsertGymBoardFailedException();
        }

        productService
            .insertProductList(createGymBoardRequest.getProductList(), simpleGymBoard.getId());

    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CacheNames.GYMBOARD, key = "#page")
    public List<GetGymBoardResponse> getGymBoards(int page, int size) {
        return gymBoardMapper.findGymBoardsByPage(page * size, size).stream()
            .map(GetGymBoardResponse::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GetGymBoardResponse getGymBoard(Long id) {
        GymBoardDTO gymBoardDTO = Optional.ofNullable(gymBoardMapper.findGymBoardById(id))
            .orElseThrow(GymBoardNotFoundException::new);

        return GetGymBoardResponse.of(gymBoardDTO);
    }

    @Transactional
    @CacheEvict(cacheNames = CacheNames.GYMBOARD, allEntries = true)
    public void updateGymBoard(
        long gymBoardId, Long loginId, UpdateGymBoardRequest updateGymBoardRequest
    ) {
        updateGymBoardRequest.getUserType().validEqualUserType("GYM");

        Long gymId = gymBoardMapper.findGymIdByGymBoardId(gymBoardId);

        if (!loginId.equals(gymId)) {
            throw new LoginUserDoesNotMatchException(loginId);
        }

        if (gymBoardMapper.updateGymBoard(UpdateGymBoardRequest.of(gymBoardId, updateGymBoardRequest)) != 1) {
            throw new UpdateGymBoardFailedException(
                "데이터베이스에 GymBoard update 실패. GymBoardId: " + gymBoardId);
        }

        updateProductList(updateGymBoardRequest);
    }

    private void updateProductList(UpdateGymBoardRequest updateGymBoardRequest) {

        if (updateGymBoardRequest.getUpdateProductList() != null) {
            productService.updateProductList(updateGymBoardRequest.getUpdateProductList(), updateGymBoardRequest.getGymboardId());
        }

        if (updateGymBoardRequest.getCreateProductList() != null) {
            productService.insertProductList(updateGymBoardRequest.getCreateProductList(), updateGymBoardRequest.getGymboardId());
        }

        if (updateGymBoardRequest.getDeleteProductIdList() != null) {
            productService.deleteProductList(updateGymBoardRequest.getDeleteProductIdList(), updateGymBoardRequest.getGymboardId());
        }
    }
}
