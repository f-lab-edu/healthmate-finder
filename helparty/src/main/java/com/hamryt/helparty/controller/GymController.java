package com.hamryt.helparty.controller;

import com.hamryt.helparty.aop.ValidateUser;
import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.gym.GymService;
import com.hamryt.helparty.service.session.SessionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final SessionService sessionService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpGymResponse signUpGym(
        @Valid @RequestBody SignUpGymRequest resource
    ) {
        return gymService.insertGym(resource);
    }
    
    @LoginValidation
    @ValidateUser(value = "id", type = UserType.GYM)
    @PutMapping("{id}")
    public UpdateGymResponse updateGym(
        @PathVariable("id") Long id,
        @Valid @RequestBody UpdateGymRequest updateGymRequest
    ) {
        return gymService.updateGym(id, updateGymRequest);
    }
    
}

