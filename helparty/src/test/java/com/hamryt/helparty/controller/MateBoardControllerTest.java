package com.hamryt.helparty.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.board.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.board.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.board.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.dto.board.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.service.mateboard.MateBoardServiceImpl;
import com.hamryt.helparty.service.session.SessionServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MateBoardController.class)
public class MateBoardControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private MateBoardServiceImpl mateBoardService;
    
    @MockBean
    private SessionServiceImpl loginService;
    
    @Test
    @DisplayName("동행 구함 게시물 생성에 성공하면 해당 게시물을 반환한다.")
    public void create_Success() throws Exception {
        
        // given
        Long id = 1004L;
        String name = "test";
        String addressDetail = "seoul donjak";
        String gym = "test gym";
        String content = "test";
        String startTime = "18:00";
        String endTime = "19:00";
        UserType userType = UserType.USER;
        
        CreateMateBoardRequest createMateBoardRequest
            = getCreateRequest(gym, content, userType, startTime, endTime);
        
        CreateMateBoardResponse createMateBoardResponse
            = getCreateResponse(id, name, gym, content, addressDetail, startTime, endTime);
        
        String request = new ObjectMapper().writeValueAsString(createMateBoardRequest);
        
        given(loginService.getLoginId())
            .willReturn(1L);
        given(mateBoardService.addMateBoard(createMateBoardRequest, any()))
            .willReturn(createMateBoardResponse);
        
        // when, then
        mvc.perform(post("/mateboards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isCreated());
    }
    
    
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
    public void getMateBoard_Success() throws Exception {
        
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
    
    @Test
    @DisplayName("동행 구함 게시물 업데이트 성공하면 업데이트 게시물을 반환한다.")
    public void updateMates_Success() throws Exception {
        
        // given
        String email = "test@example.com";
        String gym = "test";
        String content = "test";
        String startTime = "08:00";
        String endTime = "10:00";
        
        UpdateMateBoardRequest updateMateBoardRequest =
            createUpdateMateBoardRequest(email, gym, content, startTime, endTime);
        
        String request = new ObjectMapper().writeValueAsString(updateMateBoardRequest);
        
        // when
        mvc.perform(patch("/mateboards/1004")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(request))
            .andExpect(status().isOk());
        
        // then
        verify(mateBoardService).updateMateBoard(any(), eq(1004L), any());
    }
    
    
    @Test
    @DisplayName("동행 구함 게시물 삭제 성공")
    public void deleteMate_Success() throws Throwable {
        
        mvc.perform(delete("/mateboards/1004")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
        
        verify(mateBoardService).deleteMateBoard(eq(1004L), any());
    }
    
    private UpdateMateBoardRequest createUpdateMateBoardRequest(String email, String gym,
        String content, String startTime,
        String endTime) {
        return UpdateMateBoardRequest.builder()
            .email(email)
            .gym(gym)
            .content(content)
            .startTime(startTime)
            .endTime(endTime)
            .build();
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
    
    private CreateMateBoardRequest getCreateRequest(
        String gym, String content, UserType userType,
        String startTime, String endTime
    ) {
        return CreateMateBoardRequest.builder()
            .gym(gym)
            .content(content)
            .userType(userType)
            .startTime(startTime)
            .endTime(endTime)
            .build();
    }
    
    private CreateMateBoardResponse getCreateResponse(
        Long id, String name, String gym,
        String content, String addressDetail,
        String startTime, String endTime
    ) {
        return CreateMateBoardResponse.builder()
            .id(id)
            .name(name)
            .gym(gym)
            .content(content)
            .addressDetail(addressDetail)
            .startTime(startTime)
            .endTime(endTime)
            .createAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .build();
    }
    
}