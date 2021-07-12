package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.exception.product.DeleteProductFailedException;
import com.hamryt.helparty.exception.product.InsertProductFailedException;
import com.hamryt.helparty.exception.product.UpdateProductFailedException;
import com.hamryt.helparty.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Transactional
    public void insertProductList(List<ProductDTO> productList, long gymBoardId) {

        if (productMapper.insertProductList(productList, gymBoardId) != productList.size()) {
            throw new InsertProductFailedException(
                "데이터베이스에 상품 리스트 insert 실패. gymBoardId: " + gymBoardId);
        }
    }

    @Transactional
    public void updateProductList(List<ProductDTO> productList, long gymBoardId) {

        if (productMapper.updateProductList(productList, gymBoardId) != 1) {
            throw new UpdateProductFailedException(
                "데이터베이스에 상품 리스트 update 실패. gymBoardId: " + gymBoardId);
        }
    }

    @Transactional
    public void deleteProductList(List<Integer> deleteProductIdList, long gymBoardId) {

        if (productMapper.deleteProductList(deleteProductIdList, gymBoardId) != 1) {
            throw new DeleteProductFailedException(
                "데이터베이스에 상품 리스트 Delete 실패. gymBoardId: " + gymBoardId);
        }
    }

}

