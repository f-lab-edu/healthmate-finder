package com.hamryt.helparty.controller;

import com.hamryt.helparty.argumentresolver.LoginId;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.gym.GymService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gyms")
@RequiredArgsConstructor
public class GymController {
    
    private final GymService gymService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpGymResponse signUpGym(
        @Valid @RequestBody SignUpGymRequest resource
    ) {
        return gymService.insertGym(resource);
    }
    
    @LoginValidation
    @PutMapping
    public UpdateGymResponse updateGym(
        @LoginId Long loginId,
        @Valid @RequestBody UpdateGymRequest updateGymRequest
    ) {
        return gymService.updateGym(loginId, updateGymRequest);
    }
    
    @LoginValidation
    @DeleteMapping
    public void deleteGym(
        @LoginId Long loginId
    ) {
        gymService.deleteGym(loginId);
    }
    
}

