package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.board.mateboard.MateBoardDTO;
import com.hamryt.helparty.dto.board.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.dto.board.mateboard.response.UpdateMateBoardResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MateBoardMapper {
    
    int insertMateBoard(MateBoardDTO mateBoard);
    
    int updateMateBoard(UpdateMateBoardResponse updateMateBoardResponse);
    
    GetMateBoardResponse findMateBoardById(Long id);
    
    List<GetMateBoardResponse> findMateBoardByPage(int index, int size);
    
    String findMateBoardEmailById(Long id);
    
    int deleteMateBoardById(Long id);
}
