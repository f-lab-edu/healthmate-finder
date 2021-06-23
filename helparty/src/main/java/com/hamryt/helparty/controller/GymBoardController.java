package com.hamryt.helparty.controller;

import com.hamryt.helparty.argumentresolver.LoginId;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.request.UpdateGymBoardRequest;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardResponse;
import com.hamryt.helparty.dto.board.gymboard.response.GetGymBoardsResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.gymboard.GymBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

        List<GetGymBoardResponse> getGymBoardResponseList = gymBoardService
            .getGymBoards(page, size);

        return GetGymBoardsResponse.of(getGymBoardResponseList, page, size);
    }

    @GetMapping("/{id}")
    public GetGymBoardResponse getGymBoard(
        @PathVariable("id") Long id
    ) {

        return gymBoardService.getGymBoard(id);
    }

    @LoginValidation
    @PutMapping("/{id}")
    public void updateGymBoard(
        @PathVariable("id") long gymBoardId,
        @LoginId Long loginId,
        @Valid @RequestBody UpdateGymBoardRequest updateGymBoardRequest
    ) {

        gymBoardService.updateGymBoard(gymBoardId, loginId, updateGymBoardRequest);
    }

}
