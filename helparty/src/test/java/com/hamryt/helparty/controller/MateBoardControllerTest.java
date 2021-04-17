package com.hamryt.helparty.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamryt.helparty.dto.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.mateboard.MateBoardService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MateBoardController.class)
public class MateBoardControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private WebTestClient webTestClient;
    
    @MockBean
    private MateBoardService mateBoardService;
    
    @MockBean
    private LoginService loginService;
    
    @Before
    void setUp(){
        webTestClient = WebTestClient.bindToWebHandler(exchange ->{
            String path = exchange.getRequest().getURI().getPath();
            if("/mateboards".equals(path)){
                return exchange.getSession()
                    .doOnNext(webSession ->
                        webSession.getAttributes().put("LOGIN_USER_EMAIL", "test@example.com"))
                    .then();
            }
            return null;
        }).build();
    }
    
    @Test
    public void create() throws Exception {
        
        String gym = "test gym";
        String content = "test";
        String startTime = "18:00";
        String endTime = "19:00";
        
        CreateMateBoardRequest createMateBoardRequest
            = CreateMateBoardRequest.builder()
            .gym(gym)
            .content(content)
            .startTime(startTime)
            .endTime(endTime)
            .build();
        
        Long id = 1004L;
        String name = "test";
        String addressDetail = "seoul donjak";
        
        CreateMateBoardResponse createMateBoardResponse
            = CreateMateBoardResponse.builder()
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
        
        String request = new ObjectMapper().writeValueAsString(createMateBoardRequest);
        
        given(loginService.getSessionEmail())
            .willReturn("test@example.com");
        given(mateBoardService.addMateBoard(createMateBoardRequest, "test@example.com"))
            .willReturn(createMateBoardResponse);
        
        mvc.perform(post("/mateboards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isCreated());
        
    }
    

    @Test
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
    public void GetLsitMateBaord_Success_integration() throws Exception {
        mvc.perform(get("/mateboards")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }
    
    @Test
    public void updateMates() throws Exception {
    
        UpdateMateBoardRequest updateMateBoardRequest =
            createUpdateMateBoardRequest("test", "test", "08:00", "10:00");
    
        String request = new ObjectMapper().writeValueAsString(updateMateBoardRequest);
        
        mvc.perform(patch("/mateboards/1004")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(request))
            .andExpect(status().isOk());
    
        verify(mateBoardService).updateMateBoard(eq(1004L), any());
    }
    
    private UpdateMateBoardRequest createUpdateMateBoardRequest(String gym, String content, String startTime,
        String endTime) {
        return UpdateMateBoardRequest.builder()
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
    
}