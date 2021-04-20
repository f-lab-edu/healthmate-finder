package com.hamryt.helparty.service.mateboard;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.hamryt.helparty.dto.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.UpdateMateBoardResponse;
import com.hamryt.helparty.mapper.MateBoardMapper;
import com.hamryt.helparty.service.user.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("get MateBoard list Success")
    public void getMates() {
        
        mockMateBoardMapper();
        
        List<GetMateBoardResponse> getMateBoardResponses =
            mateBoardService.getMates(0, 10);
        
        assertEquals(getMateBoardResponses.get(0).getContent(), "test");
    }
    
    @Test
    @DisplayName("update MateBoard Success")
    public void updateMate() {
        
        UpdateMateBoardRequest updateMateBoardRequest =
            createUpdateMateBoardRequest("test@example.com", "test", "test", "08:00", "10:00");
        
        UpdateMateBoardResponse updateMateBoardResponse =
            createUpdateMateBoardResponse(1004L, "test", "test", "08:00", "10:00",
                LocalDateTime.now());
        
        given(mateBoardMapper.updateMateBoard(any())).willReturn(1);
        
        UpdateMateBoardResponse result = mateBoardService
            .updateMateBoard(1004L, "test@example.com", updateMateBoardRequest);
        
        assertEquals(result.getContent(), updateMateBoardResponse.getContent());
        
    }
    
    private UpdateMateBoardResponse createUpdateMateBoardResponse(
        Long id, String gym, String content, String startTime,
        String endTime, LocalDateTime modifiedAt
    ) {
        return UpdateMateBoardResponse.builder()
            .id(id)
            .gym(gym)
            .content(content)
            .startTime(startTime)
            .endTime(endTime)
            .modifiedAt(modifiedAt)
            .build();
    }
    
    private UpdateMateBoardRequest createUpdateMateBoardRequest(
        String email, String gym, String content,
        String startTime, String endTime
    ) {
        return UpdateMateBoardRequest.builder()
            .email(email)
            .gym(gym)
            .content(content)
            .startTime(startTime)
            .endTime(endTime)
            .build();
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