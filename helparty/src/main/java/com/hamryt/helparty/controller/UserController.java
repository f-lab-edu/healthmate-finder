package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.login.LoginDTO;
import com.hamryt.helparty.dto.login.request.LoginRequest;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.request.UserDeleteRequest;
import com.hamryt.helparty.dto.user.response.SignUpUserResponse;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import com.hamryt.helparty.interceptor.LoginValidation;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.login.UserLoginServiceImpl;
import com.hamryt.helparty.service.session.SessionService;
import com.hamryt.helparty.service.user.UserService;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {
    
    private SessionService sessionService;
    private UserService userService;
    private LoginService loginService;
    
    
    public UserController(UserLoginServiceImpl userLoginService,
        SessionService sessionService, UserService userService) {
        this.loginService = userLoginService;
        this.sessionService = sessionService;
        this.userService = userService;
    }
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponse loginUser(
        @Valid @RequestBody LoginRequest loginRequest
    ) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        LoginDTO loginDTO = loginService.login(email, password);
        
        return UserLoginResponse.builder()
            .id(loginDTO.getId())
            .email(loginDTO.getEmail())
            .userName(loginDTO.getName())
            .addressCode(loginDTO.getAddressCode())
            .addressDetail(loginDTO.getAddressDetail())
            .userType(loginDTO.getUserType())
            .build();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpUserResponse signUpUser(
        @Valid @RequestBody SignUpUserRequest resource
    ) {
        return userService.insertUser(resource);
    }
    
    @LoginValidation
    @PutMapping("{id}")
    public UpdateUserResponse updateUser(
        @PathVariable("id") Long id,
        @Valid @RequestBody UpdateUserReqeust updateUserRequest
    ) {
        sessionService.validateUser(id);
        return userService.updateUser(id, updateUserRequest);
    }
    
    @LoginValidation
    @DeleteMapping("{id}")
    public void deleteUser(
        @PathVariable("id") Long id,
        @Valid @RequestBody UserDeleteRequest userDeleteRequest
    ) {
        sessionService.validateUser(id);
        userService.deleteUser(userDeleteRequest);
    }
    
    
    @Getter
    @NoArgsConstructor
    private static class UserLoginResponse {
        
        private Long id;
        
        private String email;
        
        private String userName;
        
        private String phoneNumber;
        
        private String addressCode;
        
        private String addressDetail;
        
        private UserType userType;
        
        @Builder
        public UserLoginResponse(
            Long id, String email, String userName, String phoneNumber,
            String addressCode, String addressDetail, UserType userType
        ) {
            this.id = id;
            this.email = email;
            this.userName = userName;
            this.phoneNumber = phoneNumber;
            this.addressCode = addressCode;
            this.addressDetail = addressDetail;
            this.userType = userType;
        }
        
    }
}
