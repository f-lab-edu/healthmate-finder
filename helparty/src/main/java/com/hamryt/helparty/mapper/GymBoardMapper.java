package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.board.gymboard.SimpleGymBoard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GymBoardMapper {
    
    int insertGymBoard(SimpleGymBoard simpleGymBoard);
}
