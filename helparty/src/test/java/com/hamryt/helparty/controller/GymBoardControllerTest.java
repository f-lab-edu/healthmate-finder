package com.hamryt.helparty.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.dto.board.product.request.SimpleProduct;
import com.hamryt.helparty.service.gymboard.GymBoardServiceImpl;
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
@WebMvcTest(GymBoardController.class)
class GymBoardControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private GymBoardServiceImpl gymBoardService;
    
    String title = "test";
    String content = "test";
    String price = "test";
    String scope = "test";
    
    ObjectMapper mapper = new ObjectMapper();
    
    @Test
    @DisplayName("운동시설 게시물 생성 성공하면 해당 계정 정보를 반환한다.")
    public void create_Success() throws Exception {
        
        SimpleProduct mockSimpleProduct =
            getSimpleProduct(1L, title, content, price, scope, BoardType.GYM);
        
        CreateGymBoardRequest mockCreateGymBoardRequest =
            getCreateGymBoardRequest(title, content, mockSimpleProduct);
        
        String request = mapper.writeValueAsString(mockCreateGymBoardRequest);
        
        doNothing().when(gymBoardService).insertGymBoard(any(), any());
        
        mvc.perform(post("/gymboards")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(request))
            .andExpect(status().isCreated());
    }
    
    private CreateGymBoardRequest getCreateGymBoardRequest(String title, String content,
        SimpleProduct simpleProduct) {
        return CreateGymBoardRequest.builder()
            .title(title)
            .content(content)
            .userType(UserType.GYM)
            .simpleProduct(simpleProduct)
            .build();
    }
    
    private SimpleProduct getSimpleProduct(Long id, String title, String content, String price,
        String scope, BoardType gym) {
        return SimpleProduct.builder()
            .id(1L)
            .title(title)
            .content(content)
            .price(price)
            .scope(scope)
            .boardType(BoardType.GYM)
            .build();
    }
}