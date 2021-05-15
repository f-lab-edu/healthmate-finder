package com.hamryt.helparty.controller;

import com.hamryt.helparty.service.gymboard.GymBoardServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GymBoardController.class)
class GymBoardControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private GymBoardServiceImpl gymBoardService;
    
    @Test
    @DisplayName("운동시설 게시물 생성 성공하면 해당 계정 정보를 반환한다.")
    public void create_Success() {
    
    }
}