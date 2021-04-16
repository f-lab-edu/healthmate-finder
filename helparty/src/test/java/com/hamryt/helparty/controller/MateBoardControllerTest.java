package com.hamryt.helparty.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamryt.helparty.dto.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.service.login.LoginService;
import com.hamryt.helparty.service.mateboard.MateBoardService;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
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
    
}