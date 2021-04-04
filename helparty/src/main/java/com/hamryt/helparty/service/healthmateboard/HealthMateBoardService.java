package com.hamryt.helparty.service.healthmateboard;

import com.hamryt.helparty.dto.healthmateboard.request.CreateHealthMateBoardRequest;
import com.hamryt.helparty.dto.healthmateboard.response.CreateHealthMateBoardResponse;

public interface HealthMateBoardService {

    CreateHealthMateBoardResponse insertHealthMateBoard(CreateHealthMateBoardRequest createHealthMateBoardRequest, String email);
}
