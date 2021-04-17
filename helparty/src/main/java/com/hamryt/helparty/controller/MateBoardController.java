package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.GetMatesBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.UpdateMateBoardResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.mateboard.MateBoardService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    
    @GetMapping
    public GetMatesBoardResponse getMates(
        @RequestParam(defaultValue = "0") @Range(min = 0, max = Integer.MAX_VALUE) int page,
        @RequestParam(defaultValue = "10") @Range(min = 1, max = 10) int size
    ) {
        List<GetMateBoardResponse> getMateResponseList = mateBoardService.getMates(page, size);
        return GetMatesBoardResponse.builder()
            .mateBoardList(getMateResponseList)
            .page(page)
            .size(size)
            .build();
    }
    
    @LoginValidation
    @PatchMapping("/{id}")
    public UpdateMateBoardResponse updateMateBoard(
        @PathVariable Long id,
        @Valid @RequestBody UpdateMateBoardRequest updateMateBoardRequest
    ){
        String email = loginService.getSessionEmail();
        return mateBoardService.updateMateBoard(id, email, updateMateBoardRequest);
    }
    
    @GetMapping("/{id}")
    public GetMateBoardResponse getMate(
      @PathVariable Long id
    ){
        return mateBoardService.getMate(id);
    }
}
