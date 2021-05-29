package com.hamryt.helparty.service.gymboard;

import com.hamryt.helparty.dto.board.gymboard.GymBoardDTO;
import com.hamryt.helparty.dto.board.gymboard.SimpleGymBoard;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardResponse;
import com.hamryt.helparty.exception.board.gymboard.GymBoardNotFoundException;
import com.hamryt.helparty.exception.board.gymboard.InsertGymBoardFailedException;
import com.hamryt.helparty.mapper.GymBoardMapper;
import com.hamryt.helparty.service.product.ProductService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GymBoardServiceImpl implements GymBoardService {
    
    private final GymBoardMapper gymBoardMapper;
    private final ProductService productService;
    
    @Transactional
    public void insertGymBoard(CreateGymBoardRequest createGymBoardRequest, Long loginId) {
        
        SimpleGymBoard simpleGymBoard = SimpleGymBoard.of(createGymBoardRequest, loginId);
        
        if (gymBoardMapper.insertGymBoard(simpleGymBoard) != 1) {
            log.error("Insert GymBoard query failed : " + simpleGymBoard);
            throw new InsertGymBoardFailedException();
        }
        
        productService
            .insertProduct(createGymBoardRequest.getProductList(), simpleGymBoard.getId());
    }
    
    @Transactional(readOnly = true)
    @Cacheable(value = "gymboards")
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
}
