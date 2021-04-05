package com.hamryt.helparty.mapper.mateboard;

import com.hamryt.helparty.dto.mateboard.MateBoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MateBoardMapper {

    int insertMateBoard(MateBoardDTO mateBoard);

}
