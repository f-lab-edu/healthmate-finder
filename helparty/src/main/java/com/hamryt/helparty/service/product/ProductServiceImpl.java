package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.exception.product.InsertProductFailedException;
import com.hamryt.helparty.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductMapper productMapper;
    
    @Transactional
    public void insertProduct(List<ProductDTO> productList, Long gymBoardId) {
        
        if (productMapper.insertProductList(productList, gymBoardId) != productList.size()) {
            System.out.println();
            log.error("Insert Product query failed ");
            throw new InsertProductFailedException();
        }
    }
}

