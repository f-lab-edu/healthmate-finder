package com.hamryt.helparty.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.mateboard.MateBoardService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MateBoardController.class)
public class MateBoardControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private MateBoardService mateBoardService;
    
    @MockBean
    private LoginService loginService;
    
    @Test
    @DisplayName("동행 구함 게시물 페이지 조회에 성공하면 HTTP 상태코드 200과 게시물 응답 정보를 반환한다.")
    public void getListMateBoard_Success() throws Exception {
        
        mockMateBoardMapper();
        
        mvc.perform(get("/mateboards?page=0&size=10"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"id\":1004")
            ))
            .andExpect(content().string(
                containsString("\"userName\":\"test\"")
            ));
        
    }
    
    @Test
    @DisplayName("동행 구함 게시물 조회에 성공하면 HTTP 상태코드 200과 게시물 응답 정보를 반환한다.")
    public void getMateBoard() throws Exception {
        
        GetMateBoardResponse getMateBoardResponse =
            GetMateBoardResponse.builder()
                .id(1004L)
                .userName("test")
                .userAddress("test")
                .gym("test")
                .content("test")
                .startTime("08:00")
                .endTime("10:00")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
        
        given(mateBoardService.getMate(1004L)).willReturn(getMateBoardResponse);
        
        mvc.perform(get("/mateboards/1004"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"id\":1004")
            ))
            .andExpect(content().string(
                containsString("\"userName\":\"test\"")
            ));
        
    }
    
    private void mockMateBoardMapper() {
        GetMateBoardResponse getMateBoardResponse =
            GetMateBoardResponse.builder()
                .id(1004L)
                .userName("test")
                .userAddress("test")
                .gym("test")
                .content("test")
                .startTime("test")
                .endTime("test")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
        
        List<GetMateBoardResponse> getMateBoardResponseList = new ArrayList<>(
            Arrays.asList(
                getMateBoardResponse
            )
        );
        
        given(mateBoardService.getMates(0, 10)).willReturn(getMateBoardResponseList);
    }
    
}