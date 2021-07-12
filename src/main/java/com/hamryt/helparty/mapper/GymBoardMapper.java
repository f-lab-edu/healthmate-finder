package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.board.gymboard.GymBoardDTO;
import com.hamryt.helparty.dto.board.gymboard.SimpleGymBoard;
import com.hamryt.helparty.dto.board.gymboard.request.UpdateGymBoardRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GymBoardMapper {

    int insertGymBoard(SimpleGymBoard simpleGymBoard);

    List<GymBoardDTO> findGymBoardsByPage(int index, int size);

    GymBoardDTO findGymBoardById(Long id);

    int updateGymBoard(UpdateGymBoardRequest updateGymBoardRequest);

    Long findGymIdByGymBoardId(long gymBoardId);
}
