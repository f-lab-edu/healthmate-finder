package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import java.util.List;

public interface ProductService {
    
    void insertProduct(List<ProductDTO> productList, Long gymBoardId);
}
