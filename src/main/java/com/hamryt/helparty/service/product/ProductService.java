package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO;

import java.util.List;

public interface ProductService {

    void insertProductList(List<ProductDTO> productList, Long gymBoardId);

    void updateProductList(List<ProductDTO> productList, Long gymBoardId);

    void deleteProductList(List<Integer> deleteProductIdList, Long gymBoardId);
}
