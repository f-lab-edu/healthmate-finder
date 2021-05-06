package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.service.gym.GymService;
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
@RequestMapping("gyms")
public class GymController {
    
    private final GymService gymService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpGymResponse signUpGym(
        @Valid @RequestBody SignUpGymRequest resource
    ) {
        return gymService.insertGym(resource);
    }
    
}
