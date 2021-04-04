package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.healthmateboard.request.CreateHealthMateBoardRequest;
import com.hamryt.helparty.dto.healthmateboard.response.CreateHealthMateBoardResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.healthmateboard.HealthMateBoardService;
import com.hamryt.helparty.service.login.LoginService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("healthmateboards")
public class HealthMateBoardController {

    private final HealthMateBoardService healthMateBoardService;
    private final LoginService loginService;

    @LoginValidation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateHealthMateBoardResponse createHealthMateBoard(
        @Valid @RequestBody CreateHealthMateBoardRequest createHealthMateBoardRequest
    ) {
        String email = loginService.getSessionEmail();
        return healthMateBoardService.insertHealthMateBoard(createHealthMateBoardRequest, email);
    }

}
