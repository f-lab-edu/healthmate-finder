package com.hamryt.helparty.controller;


import com.hamryt.helparty.argumentresolver.LoginId;
import com.hamryt.helparty.dto.board.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.board.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.board.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.dto.board.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.dto.board.mateboard.response.GetMateBoardsResponse;
import com.hamryt.helparty.dto.board.mateboard.response.UpdateMateBoardResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.mateboard.MateBoardService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("mateboards")
public class MateBoardController {
    
    private final MateBoardService mateBoardService;
    
    
    @LoginValidation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMateBoardResponse createMateBoard(
        @Valid @RequestBody CreateMateBoardRequest createMateBoardRequest,
        @LoginId Long loginId
    ) {
        return mateBoardService.addMateBoard(createMateBoardRequest, loginId);
    }
    
    @GetMapping("/{id}")
    public GetMateBoardResponse getMate(
        @PathVariable Long id
    ) {
        return mateBoardService.getMate(id);
    }
    
    @GetMapping
    public GetMateBoardsResponse getMateBoards(
        @RequestParam(defaultValue = "0") @Range(min = 0, max = Integer.MAX_VALUE) int page,
        @RequestParam(defaultValue = "10") @Range(min = 1, max = 10) int size
    ) {
        List<GetMateBoardResponse> getMateResponseList = mateBoardService.getMates(page, size);
        return GetMateBoardsResponse.builder()
            .mateBoardList(getMateResponseList)
            .page(page)
            .size(size)
            .build();
    }
    
    @LoginValidation
    @PatchMapping("/{id}")
    public UpdateMateBoardResponse updateMateBoard(
        @PathVariable("id") long boardId,
        @LoginId Long loginId,
        @Valid @RequestBody UpdateMateBoardRequest updateMateBoardRequest
    ) {
        return mateBoardService.updateMateBoard(loginId, boardId, updateMateBoardRequest);
    }
    
    @LoginValidation
    @DeleteMapping("/{id}")
    public void deleteMateBoard(
        @PathVariable("id") long boardId,
        @LoginId Long loginId
    ) {
        mateBoardService.deleteMateBoard(boardId, loginId);
    }
    
}
