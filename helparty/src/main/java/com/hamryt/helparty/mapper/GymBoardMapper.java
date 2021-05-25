package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.board.gymboard.GymBoardDTO;
import com.hamryt.helparty.dto.board.gymboard.SimpleGymBoard;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GymBoardMapper {
    
    int insertGymBoard(SimpleGymBoard simpleGymBoard);
    
    List<GymBoardDTO> findGymBoardsByPage(int index, int size);
}
