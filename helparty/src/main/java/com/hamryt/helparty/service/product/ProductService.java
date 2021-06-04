package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.dto.board.product.request.SimpleProduct;

public interface ProductService {
    
    void insertProduct(SimpleProduct simpleProduct, BoardType authType);
}
