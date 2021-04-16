package com.hamryt.helparty.service.mateboard;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.mapper.MateBoardMapper;
import com.hamryt.helparty.service.user.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MateBoardServiceImplTest {
    
    @InjectMocks
    private MateBoardServiceImpl mateBoardService;
    
    @Mock
    private MateBoardMapper mateBoardMapper;
    
    @Mock
    private UserService userService;
    
    @Test
    public void getMates() {
        
        mockMateBoardMapper();
        
        List<GetMateBoardResponse> getMateBoardResponses =
            mateBoardService.getMates(0, 10);
        
        assertEquals(getMateBoardResponses.get(0).getContent(), "test");
    }
    
    private void mockMateBoardMapper() {
        GetMateBoardResponse mockResponse =
            GetMateBoardResponse.builder()
                .id(1004L)
                .userName("test")
                .userAddress("test")
                .gym("test")
                .content("test")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
        
        List<GetMateBoardResponse> getMateBoardResponseInput = new ArrayList<>();
        getMateBoardResponseInput.add(mockResponse);
        
        given(mateBoardMapper.findMateBoardByPage(0, 10)).willReturn(getMateBoardResponseInput);
    }
    
}