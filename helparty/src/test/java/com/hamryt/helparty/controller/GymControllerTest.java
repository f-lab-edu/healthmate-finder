package com.hamryt.helparty.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse;
import com.hamryt.helparty.service.gym.GymServiceImpl;
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
@WebMvcTest(GymController.class)
class GymControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private GymServiceImpl gymService;
    
    ObjectMapper mapper = new ObjectMapper();
    
    String gymName = "testGym";
    String password = "123";
    String phoneNumber = "01012345678";
    String addressCode = "1234";
    String addressDetail = "Seoul";
    UserType userType = UserType.GYM;
    
    @Test
    @DisplayName("운동시설 관리자 계정 생성 성공하면 해당 계정 정보를 반환한다.")
    public void create_Success() throws Exception {
        
        String email = "test@example.com";
        
        SignUpGymResponse signUpGymResponse =
            getSignGymResponse(1004L, email, gymName, phoneNumber, addressCode, addressDetail,
                userType);
        
        SignUpGymRequest signUpGymRequest =
            getSignGymRequest(email, gymName, password, phoneNumber, addressCode, addressDetail,
                userType);
        
        String request = mapper.writeValueAsString(signUpGymRequest);
        given(gymService.insertGym(any())).willReturn(signUpGymResponse);
        
        mvc.perform(post("/gyms")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(request))
            .andExpect(status().isCreated());
        
        verify(gymService).insertGym(any());
    }
    
    private UpdateGymRequest getUpdateGymRequest(String gymName, String password,
        String phoneNumber, String addressCode, String addressDetail, UserType userType) {
        return UpdateGymRequest
            .create(gymName, password, phoneNumber, addressCode, addressDetail, userType);
    }
    
    private SignUpGymRequest getSignGymRequest(String email, String gymName, String password,
        String phoneNumber, String addressCode, String addressDetail, UserType userType) {
        return SignUpGymRequest
            .create(email, gymName, password, phoneNumber, addressCode, addressDetail, userType);
        
    }
    
    private UpdateGymResponse getUpdateGymResponse(Long id, String password, String phoneNumber,
        String addressCode, String addressDetail) {
        return UpdateGymResponse.builder()
            .id(id)
            .password(password)
            .phoneNumber(phoneNumber)
            .addressCode(addressCode)
            .addressDetail(addressDetail)
            .build();
    }
    
    private SignUpGymResponse getSignGymResponse(Long id, String email, String gymName,
        String phoneNumber, String addressCode, String addressDetail, UserType userType) {
        return SignUpGymResponse.builder()
            .id(id)
            .email(email)
            .gymName(gymName)
            .phoneNumber(phoneNumber)
            .addressCode(addressCode)
            .addressDetail(addressDetail)
            .userType(userType)
            .build();
    }
}