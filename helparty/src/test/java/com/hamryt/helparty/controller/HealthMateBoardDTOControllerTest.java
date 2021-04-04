package com.hamryt.helparty.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hamryt.helparty.dto.healthmateboard.request.CreateHealthMateBoardRequest;
import com.hamryt.helparty.service.healthmateboard.HealthMateBoardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HealthMateBoardController.class)
class HealthMateBoardDTOControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HealthMateBoardService healthMateBoardService;

    @Test
    public void create() throws Exception {

    }

}