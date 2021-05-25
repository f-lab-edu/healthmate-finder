package com.hamryt.helparty.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamryt.helparty.dto.board.gymboard.request.CreateGymBoardRequest;
import com.hamryt.helparty.dto.board.product.ProductDTO;
import com.hamryt.helparty.dto.board.product.ProductDTO.BoardType;
import com.hamryt.helparty.service.gymboard.GymBoardServiceImpl;
import java.util.ArrayList;
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
        
        ProductDTO mockProduct =
            getProductDTO(1L, title, content, price, scope, BoardType.GYM);
        
        List<ProductDTO> mockProductList = new ArrayList<>();
        
        mockProductList.add(mockProduct);
        
        CreateGymBoardRequest mockCreateGymBoardRequest =
            getCreateGymBoardRequest(title, content, mockProductList);
        
        String request = mapper.writeValueAsString(mockCreateGymBoardRequest);
        
        mvc.perform(post("/gymboards")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(request))
            .andExpect(status().isCreated());
    }
    
    private CreateGymBoardRequest getCreateGymBoardRequest(String title, String content,
        List<ProductDTO> productDTOList) {
        return CreateGymBoardRequest.builder()
            .title(title)
            .content(content)
            .productList(productDTOList)
            .build();
    }
    
    private ProductDTO getProductDTO(Long id, String title, String content, String price,
        String scope, BoardType gym) {
        return ProductDTO.builder()
            .id(1L)
            .title(title)
            .content(content)
            .price(price)
            .scope(scope)
            .boardType(BoardType.GYM)
            .build();
    }
}