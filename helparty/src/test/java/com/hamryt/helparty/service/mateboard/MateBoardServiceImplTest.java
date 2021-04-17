package com.hamryt.helparty.service.mateboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.mapper.MateBoardMapper;
import com.hamryt.helparty.service.user.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MateBoardServiceImplTest {
    
    @InjectMocks
    private MateBoardServiceImpl mateBoardService;
    
    @Mock
    private MateBoardMapper mateBoardMapper;
    
    @Mock
    private UserService userService;
    
    @Test
    public void getMateBoard() {
        
        mockGetMateBoardById();
        
        Long id = 1004L;
        GetMateBoardResponse getMateBoardResponse = mateBoardService.getMate(id);
        
        assertEquals(getMateBoardResponse.getId(), 1004L);
    }
    
    private void mockGetMateBoardById() {
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
        
        given(mateBoardMapper.findMateBoardById(eq(1004L))).willReturn(getMateBoardResponse);
    }
    
}