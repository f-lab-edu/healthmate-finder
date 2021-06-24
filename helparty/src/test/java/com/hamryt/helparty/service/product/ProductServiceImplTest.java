package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.exception.product.DeleteProductFailedException;
import com.hamryt.helparty.exception.product.InsertProductFailedException;
import com.hamryt.helparty.exception.product.UpdateProductFailedException;
import com.hamryt.helparty.mapper.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

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

    private List<ProductDTO> mockProductList = makeMockProductList();


    @Test
    @DisplayName("상품 생성 성공")
    public void insertProductList_Success() {

        given(productMapper.insertProductList(any(), eq(1004L))).willReturn(1L);

        productService.insertProductList(mockProductList, 1004L);
    }

    @Test
    @DisplayName("상품 생성 실패 : 데이터베이스 insert 명령에 실패하면 InsertProductFailedException을 발생시킨다.")
    public void insertProductList_Fail_InsertProductFailedException() {

        given(productMapper.insertProductList(any(), eq(1004L))).willReturn(0L);

        InsertProductFailedException insertProductFailedException
            = assertThrows(InsertProductFailedException.class,
            () -> productService.insertProductList(mockProductList, 1004));

        assertEquals("데이터베이스에 상품 리스트 insert 실패. gymBoardId: " + 1004,
            insertProductFailedException.getMessage());
    }

    @Test
    @DisplayName("상품 수정 성공")
    public void updateProductList_Success() {

        given(productMapper.updateProductList(any(), eq(1004L))).willReturn(1);

        productService.updateProductList(mockProductList, 1004L);
    }

    @Test
    @DisplayName("상품 수정 실패 : 데이터베이스 update 명령에 실패하면 UpdateProductFailedException를 발생시킨다.")
    public void updateProductList_Fail_UpdateProductFailedException() {

        given(productMapper.updateProductList(any(), eq(1004L))).willReturn(0);

        UpdateProductFailedException updateProductFailedException
            = assertThrows(UpdateProductFailedException.class,
            () -> productService.updateProductList(mockProductList, 1004L));

        assertEquals("데이터베이스에 상품 리스트 update 실패. gymBoardId: 1004", updateProductFailedException.getMessage());
    }

    @Test
    @DisplayName("상품 삭제 성공")
    public void deleteProductList_Success() {

        List<Integer> deleteProductIdList = new ArrayList<>();
        deleteProductIdList.add(1);

        given(productMapper.deleteProductList(any(), eq(1004L))).willReturn(1);

        productService.deleteProductList(deleteProductIdList, 1004L);
    }

    @Test
    @DisplayName("상품 삭제 실패 : 데이터베이스에 delete 명령 실패시 DeleteProductFailedException를 발생시킨다.")
    public void deleteProductList_Fail_DeleteProductFailedException() {

        List<Integer> deleteProductIdList = new ArrayList<>();
        deleteProductIdList.add(1);

        given(productMapper.deleteProductList(any(), eq(1004L))).willReturn(0);

        DeleteProductFailedException deleteProductFailedException
            = assertThrows(DeleteProductFailedException.class,
            () -> productService.deleteProductList(deleteProductIdList, 1004));

        assertEquals("데이터베이스에 상품 리스트 Delete 실패. gymBoardId: 1004", deleteProductFailedException.getMessage());
    }

    private List<ProductDTO> makeMockProductList() {
        List<ProductDTO> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);
        return mockProductList;
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