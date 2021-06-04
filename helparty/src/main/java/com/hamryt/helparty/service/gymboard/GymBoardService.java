package com.hamryt.helparty.service.gymboard;

import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;

public interface GymBoardService {
    
    void insertGymBoard(CreateGymBoardRequest createGymBoardRequest, Long loginId);
}
