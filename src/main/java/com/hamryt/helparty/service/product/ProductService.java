package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO;

import java.util.List;

public interface ProductService {

    void insertProductList(List<ProductDTO> productList, long gymBoardId);

    void updateProductList(List<ProductDTO> productList, long gymBoardId);

    void deleteProductList(List<Integer> deleteProductIdList, long gymBoardId);
}
