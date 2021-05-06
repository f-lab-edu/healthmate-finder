package com.hamryt.helparty.controller;


import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.session.SessionService;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {
    
    private final LoginService<UserDTO> userLoginService;
    private final LoginService<GymDTO> gymLoginService;
    
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse loginUser(
        @Valid @RequestBody LoginRequest loginRequest
    ) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        UserDTO userDto = userLoginService.login(email, password);
        
        return new LoginResponse(userDto);
    }
    
    @PostMapping("/gym")
    @ResponseStatus(HttpStatus.OK)
    public GymLoginResponse loginGym(
        @Valid @RequestBody GymLoginRequest gymLoginRequest
    ) {
        String email = gymLoginRequest.email;
        String password = gymLoginRequest.password;
        
        GymDTO gymDTO = gymLoginService.login(email, password);
        
        return GymLoginResponse.builder()
            .id(gymDTO.getId())
            .email(gymDTO.getEmail())
            .gymName(gymDTO.getGymName())
            .addressCode(gymDTO.getAddressCode())
            .addressDetail(gymDTO.getAddressDetail())
            .userType(gymDTO.getUserType())
            .build();
    }
    
    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor
    private static class GymLoginRequest {
        
        @NonNull
        private String email;
        
        @NonNull
        private String password;
    }
    
    @Getter
    @NoArgsConstructor
    private static class GymLoginResponse {
        
        private Long id;
        
        private String email;
        
        private String gymName;
        
        private String addressCode;
        
        private String addressDetail;
        
        private UserType userType;
        
        @Builder
        private GymLoginResponse(
            Long id, String email, String gymName,
            String addressCode, String addressDetail,
            UserType userType
        ) {
            this.id = id;
            this.email = email;
            this.gymName = gymName;
            this.addressCode = addressCode;
            this.addressDetail = addressDetail;
            this.userType = userType;
        }
    }
    
    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor
    private static class LoginRequest {
        
        @NonNull
        private String email;
        
        @NonNull
        private String password;
    }
    
    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor
    private static class LoginResponse {
        
        @NonNull
        private UserDTO userDto;
        
    }
    
}
