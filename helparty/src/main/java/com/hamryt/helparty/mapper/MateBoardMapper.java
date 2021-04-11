package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.mateboard.MateBoardDTO;
import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MateBoardMapper {

    int insertMateBoard(MateBoardDTO mateBoard);
    
    List<GetMateBoardResponse> findMateBoardByPage(int page, int size);
}
