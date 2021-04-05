package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.mateboard.MateBoardService;
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
@RequestMapping("mateboards")
public class MateBoardController {

    private final MateBoardService mateBoardService;
    private final LoginService loginService;

    @LoginValidation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMateBoardResponse createMateBoard(
        @Valid @RequestBody CreateMateBoardRequest createMateBoardRequest
    ) {
        String email = loginService.getSessionEmail();
        return mateBoardService.addMateBoard(createMateBoardRequest, email);
    }

}
