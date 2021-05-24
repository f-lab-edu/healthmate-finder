package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.board.product.request.SimpleProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    
    Long insertProduct(SimpleProduct simpleProduct);
}
