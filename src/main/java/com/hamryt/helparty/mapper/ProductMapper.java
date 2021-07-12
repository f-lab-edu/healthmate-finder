package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.board.product.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    Long insertProductList(List<ProductDTO> productList, long gymBoardId);

    int updateProductList(List<ProductDTO> productList, long gymBoardId);

    int deleteProductList(List<Integer> deleteProductIdList, long gymBoardId);
}
