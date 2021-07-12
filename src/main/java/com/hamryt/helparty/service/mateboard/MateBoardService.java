package com.hamryt.helparty.service.mateboard;

import com.hamryt.helparty.dto.board.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.board.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.board.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.dto.board.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.dto.board.mateboard.response.UpdateMateBoardResponse;
import java.util.List;

public interface MateBoardService {
    
    CreateMateBoardResponse addMateBoard(
        CreateMateBoardRequest createMateBoardRequest, Long id);
    
    List<GetMateBoardResponse> getMates(int page, int size);
    
    UpdateMateBoardResponse updateMateBoard(Long loginId, long boardId,
        UpdateMateBoardRequest updateMateBoardRequest);
    
    GetMateBoardResponse getMate(Long id);
    
    void deleteMateBoard(long boardId, Long loginId);
}
