package com.hamryt.helparty.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.hamryt.helparty.service.mateboard.MateBoardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(MateBoardController.class)
class MateBoardDTOControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MateBoardService mateBoardService;

    @Test
    public void create() throws Exception {

    }

}