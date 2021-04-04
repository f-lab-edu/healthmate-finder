package com.hamryt.helparty.mapper.healthmateboard;

import com.hamryt.helparty.dto.healthmateboard.HealthMateBoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HealthMateBoardMapper {

    int insertHealthMateBoard(HealthMateBoardDTO healthMateBoard);

}
