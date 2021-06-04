package com.hamryt.helparty.service.product;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.dto.board.product.request.SimpleProduct;
import com.hamryt.helparty.exception.product.InsertProductFailedException;
import com.hamryt.helparty.mapper.ProductMapper;
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
    public void insertProduct(SimpleProduct simpleProduct, BoardType authType) {
        ProductDTO.checkBoardType(simpleProduct.getBoardType(), authType);
        
        if (productMapper.insertProduct(simpleProduct) != 1) {
            log.error("Insert Product query failed : " + simpleProduct);
            throw new InsertProductFailedException();
        }
    }
}

