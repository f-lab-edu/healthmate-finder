package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.dto.login.LoginDTO;
import com.hamryt.helparty.dto.login.request.LoginRequest;
import com.hamryt.helparty.service.gym.GymService;
import com.hamryt.helparty.service.login.GymLoginServiceImpl;
import com.hamryt.helparty.service.login.LoginService;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gyms")
public class GymController {
    
    private GymService gymService;
    private LoginService loginService;
    
    @Autowired
    private GymController(GymLoginServiceImpl gymLoginService, GymService gymService) {
        this.loginService = gymLoginService;
        this.gymService = gymService;
    }
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public GymLoginResponse loginGym(
        @Valid @RequestBody LoginRequest loginRequest
    ) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        LoginDTO loginDTO = loginService.login(email, password);
        
        return GymLoginResponse.builder()
            .id(loginDTO.getId())
            .email(loginDTO.getEmail())
            .gymName(loginDTO.getName())
            .addressCode(loginDTO.getAddressCode())
            .addressDetail(loginDTO.getAddressDetail())
            .userType(loginDTO.getUserType())
            .build();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpGymResponse signUpGym(
        @Valid @RequestBody SignUpGymRequest resource
    ) {
        return gymService.insertGym(resource);
    }
    
    @Getter
    @NoArgsConstructor
    private static class GymLoginResponse {
        
        private Long id;
        
        private String email;
        
        private String gymName;
        
        private String phoneNumber;
        
        private String addressCode;
        
        private String addressDetail;
        
        private UserType userType;
        
        @Builder
        private GymLoginResponse(
            Long id, String email, String gymName,
            String phoneNumber, String addressCode,
            String addressDetail, UserType userType
        ) {
            this.id = id;
            this.email = email;
            this.gymName = gymName;
            this.phoneNumber = phoneNumber;
            this.addressCode = addressCode;
            this.addressDetail = addressDetail;
            this.userType = userType;
        }
    }
}
