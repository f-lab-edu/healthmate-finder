package com.hamryt.helparty.service.mateboard;

import com.hamryt.helparty.dto.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.UpdateMateBoardResponse;
import java.util.List;

public interface MateBoardService {
    
    CreateMateBoardResponse addMateBoard(
        CreateMateBoardRequest createMateBoardRequest, String email);
    
    List<GetMateBoardResponse> getMates(int page, int size);
    
    UpdateMateBoardResponse updateMateBoard(Long id, UpdateMateBoardRequest updateMateBoardRequest);
    
    GetMateBoardResponse getMate(Long id);
}
