package com.hamryt.helparty.service.gymboard;

import com.hamryt.helparty.dto.board.gymboard.SimpleGymBoard;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.exception.board.gymboard.InsertGymBoardFailedException;
import com.hamryt.helparty.mapper.GymBoardMapper;
import com.hamryt.helparty.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
