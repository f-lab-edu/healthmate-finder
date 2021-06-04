package com.hamryt.helparty.controller;

import com.hamryt.helparty.argumentresolver.LoginId;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.gymboard.GymBoardService;
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
@RequestMapping("gymboards")
public class GymBoardController {
    
    private final GymBoardService gymBoardService;
    
    @LoginValidation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGymBoard(
        @Valid @RequestBody CreateGymBoardRequest createGymBoardRequest,
        @LoginId Long loginId
    ) {
        gymBoardService.insertGymBoard(createGymBoardRequest, loginId);
    }
}
