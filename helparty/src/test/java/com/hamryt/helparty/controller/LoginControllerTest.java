package com.hamryt.helparty.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.login.LoginDTO;
import com.hamryt.helparty.service.login.GymLoginServiceImpl;
import com.hamryt.helparty.service.login.UserLoginServiceImpl;
import com.hamryt.helparty.service.login.factory.LoginFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private LoginFactory loginFactory;
    
    @MockBean
    private GymLoginServiceImpl gymLoginService;
    
    @MockBean
    private UserLoginServiceImpl userLoginService;
    
    Long id = 1004L;
    String email = "test@example.com";
    String name = "test";
    String password = "123";
    
    LoginDTO loginDTO;
    
    @Test
    @DisplayName("운동시설 관리자 로그인 성공하면 해당 계정 정보를 반환한다.")
    public void login_Gym_Success() throws Exception {
       
        UserType userType = UserType.GYM;
        
        loginDTO = createLoginDTO(id, name, userType);
    
        given(loginFactory.createLoginService(any())).willReturn(gymLoginService);
        given(gymLoginService.login(any(), any())).willReturn(loginDTO);
        
        mvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{\"email\":\"test@example.com\",\"password\":\"123\", \"UserType\":\"GYM\"}"))
            .andExpect(status().isOk());
        
        verify(gymLoginService).login(eq(email), eq(password));
    }
    
    @Test
    @DisplayName("일반 유저 로그인 성공하면 해당 계정 정보를 반환한다.")
    public void login_User_Success() throws Exception {
        
        UserType userType = UserType.USER;
    
        loginDTO = createLoginDTO(id, name, userType);
        
        given(loginFactory.createLoginService(any())).willReturn(userLoginService);
        given(userLoginService.login(any(), any())).willReturn(loginDTO);
        
        mvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{\"email\":\"test@example.com\",\"password\":\"123\", \"UserType\":\"USER\"}"))
            .andExpect(status().isOk());
        
        verify(userLoginService).login(eq(email), eq(password));
    }
    
    private LoginDTO createLoginDTO(Long id, String name, UserType userType){
        return LoginDTO.builder()
            .id(id)
            .name(name)
            .userType(userType)
            .build();
    }
    
}