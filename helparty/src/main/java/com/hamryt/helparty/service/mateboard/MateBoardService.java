package com.hamryt.helparty.service.mateboard;

import com.hamryt.helparty.dto.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.CreateMateBoardResponse;

public interface MateBoardService {

    CreateMateBoardResponse insertMateBoard(
        CreateMateBoardRequest createMateBoardRequest, String email);
}
