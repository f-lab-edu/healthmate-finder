package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    
    Long insertProductList(List<ProductDTO> productList, Long gymBoardId);
}
