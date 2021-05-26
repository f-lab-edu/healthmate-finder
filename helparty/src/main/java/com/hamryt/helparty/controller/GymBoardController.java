package com.hamryt.helparty.controller;

import com.hamryt.helparty.argumentresolver.LoginId;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardResponse;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardsResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.gymboard.GymBoardService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
    
    @GetMapping
    public GetGymBoardsResponse getGymBoards(
        @RequestParam(defaultValue = "0") @Range(min = 0, max = Integer.MAX_VALUE) int page,
        @RequestParam(defaultValue = "10") @Range(min = 1, max = 10) int size
    ) {
        log.info("]-----] GymBoardController::getGymBoards [-----[ page : {}, size : {}", page,
            size);
        
        List<GetGymBoardResponse> getGymBoardResponseList = gymBoardService
            .getGymBoards(page, size);
        
        return GetGymBoardsResponse.builder()
            .getGymBoardResponseList(getGymBoardResponseList)
            .page(page)
            .size(size)
            .build();
    }
    
    @GetMapping("/{id}")
    public GetGymBoardResponse getGymBoard(
        @PathVariable("id") Long id
    ) {
        log.info("]-----] GymBoardController::getGymBoard [-----[ id : {}", id);
        
        return gymBoardService.getGymBoard(id);
    }
    
}
