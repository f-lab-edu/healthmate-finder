package com.hamryt.helparty.service.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.exception.product.InsertProductFailedException;
import com.hamryt.helparty.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    
    @InjectMocks
    private ProductServiceImpl productService;
    
    @Mock
    private ProductMapper productMapper;
    
    String title = "test";
    String content = "test";
    String price = "test";
    String scope = "test";
    
    ProductDTO mockProduct =
        getSimpleProduct(1L, title, content, price, scope, BoardType.GYM);
    
    @Test
    @DisplayName("상품 생성 성공")
    public void insertProduct_Success() {
        List<ProductDTO> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);
        
        given(productMapper.insertProductList(any(), eq(1004L))).willReturn(1L);
        
        productService.insertProduct(mockProductList, 1004L);
        
    }
    
    @Test
    @DisplayName("상품 생성 실패 : 데이터베이스 명령에 실패하면 InsertProductFailedException을 발생시킨다.")
    public void insertProduct_Fail_InsertProductFailedException() {
        List<ProductDTO> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);
        
        given(productMapper.insertProductList(any(), any())).willReturn(0L);
        
        InsertProductFailedException insertProductFailedException
            = assertThrows(InsertProductFailedException.class,
            () -> productService.insertProduct(mockProductList, 1004L));
        
        assertEquals("Insert Product failed exception", insertProductFailedException.getMessage());
    }
    
    private ProductDTO getSimpleProduct(
        Long id, String title, String content, String price,
        String scope, BoardType gym
    ) {
        return ProductDTO.builder()
            .id(id)
            .title(title)
            .content(content)
            .price(price)
            .scope(scope)
            .boardType(gym)
            .build();
    }
    
}