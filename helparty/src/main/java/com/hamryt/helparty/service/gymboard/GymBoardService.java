package com.hamryt.helparty.service.gymboard;

import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardResponse;
import java.util.List;

public interface GymBoardService {
    
    void insertGymBoard(CreateGymBoardRequest createGymBoardRequest, Long loginId);
    
    List<GetGymBoardResponse> getGymBoards(int page, int size);
}
