package com.hamryt.helparty.service.gymboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.dto.board.product.request.SimpleProduct;
import com.hamryt.helparty.exception.board.gymboard.InsertGymBoardFailedException;
import com.hamryt.helparty.mapper.GymBoardMapper;
import com.hamryt.helparty.service.product.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GymBoardServiceImplTest {
    
    @InjectMocks
    private GymBoardServiceImpl gymBoardService;
    
    @Mock
    private ProductServiceImpl productService;
    
    @Mock
    private GymBoardMapper gymBoardMapper;
    
    String title = "test";
    String content = "test";
    String price = "test";
    String scope = "test";
    
    @Test
    @DisplayName("운동 시설 게시물 추가 로직 성공")
    public void insertGymBoard_Success(){
        
        SimpleProduct mockSimpleProduct =
            getSimpleProduct(1L, title, content, price, scope, BoardType.GYM);
        
        CreateGymBoardRequest createGymBoardRequest =
            getCreateGymBoardRequest(title, content, mockSimpleProduct);
    
        doNothing().when(productService).insertProduct(eq(createGymBoardRequest.getSimpleProduct()));
        
        given(gymBoardMapper.insertGymBoard(any())).willReturn(1);
        
        gymBoardService.insertGymBoard(createGymBoardRequest, 1004L);
        
    }
    
    @Test
    @DisplayName("운동 시설 게시물 추가 로직 실패 : 데이터베이스 insert 명령에 실패하면 InsertGymBoardFailedException을 발생시킨다.")
    public void insertGymBoard_Fail_InsertGymBoardFailedException(){
    
        SimpleProduct mockSimpleProduct =
            getSimpleProduct(1L, title, content, price, scope, BoardType.GYM);
    
        CreateGymBoardRequest createGymBoardRequest =
            getCreateGymBoardRequest(title, content, mockSimpleProduct);
    
        doNothing().when(productService).insertProduct(eq(createGymBoardRequest.getSimpleProduct()));
        
        given(gymBoardMapper.insertGymBoard(any())).willReturn(0);
    
        InsertGymBoardFailedException insertGymBoardFailedExcetpion
            = assertThrows(InsertGymBoardFailedException.class,
            () -> gymBoardService.insertGymBoard(createGymBoardRequest, 1004L));
        
        assertEquals("Insert GymBoard Failed Exception.", insertGymBoardFailedExcetpion.getMessage());
    
    }
    
    private CreateGymBoardRequest getCreateGymBoardRequest(
        String title, String content,
        SimpleProduct simpleProduct
    ) {
        return CreateGymBoardRequest.builder()
            .title(title)
            .content(content)
            .simpleProduct(simpleProduct)
            .build();
    }
    
    private SimpleProduct getSimpleProduct(
        Long id, String title, String content, String price,
        String scope, BoardType gym
    ) {
        return SimpleProduct.builder()
            .id(id)
            .title(title)
            .content(content)
            .price(price)
            .scope(scope)
            .boardType(gym)
            .build();
    }
    
}