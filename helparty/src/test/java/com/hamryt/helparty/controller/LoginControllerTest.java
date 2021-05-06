package com.hamryt.helparty.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.service.login.GymLoginServiceImpl;
import com.hamryt.helparty.service.login.UserLoginServiceImpl;
import com.hamryt.helparty.service.session.SessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    private UserLoginServiceImpl userLoginService;
    
    @MockBean
    private GymLoginServiceImpl gymLoginService;
    
    @Test
    @DisplayName("운동시설 관리자 로그인 성공하면 해당 계정 정보를 반환한다.")
    public void login_Success() throws Exception {
        
        String email = "test@example.com";
        String password = "123";
        
        GymDTO mockGym = GymDTO.builder()
            .email(email)
            .gymName("test")
            .addressCode("123")
            .addressDetail("seoul")
            .build();
        
        given(gymLoginService.login(any(), any())).willReturn(mockGym);
        
        mvc.perform(post("/login/gym")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"test@example.com\", \"password\":\"123\"}"))
            .andExpect(status().isOk());
        
        verify(gymLoginService).login(eq(email), eq(password));
    }
    
}