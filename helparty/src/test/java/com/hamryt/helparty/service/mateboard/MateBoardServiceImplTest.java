package com.hamryt.helparty.service.mateboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    @DisplayName("동행 구함 게시물 상세 조회 성공")
    public void getMateBoard_Success() {
        mockGetMateBoardById();
        
        Long id = 1004L;
        GetMateBoardResponse getMateBoardResponse = mateBoardService.getMate(id);
        
        assertEquals(getMateBoardResponse.getId(), 1004L);
    }
    
    @Test
    @DisplayName("동행 구함 게시물 페이지 조회 성공")
    public void getMates_Success() {
        
        mockMateBoardMapper();
        
        List<GetMateBoardResponse> getMateBoardResponses =
            mateBoardService.getMates(0, 10);
        
        assertEquals(getMateBoardResponses.get(0).getContent(), "test");
    }
    
    @Test
    @DisplayName("동행 구함 게시물 업데이트 성공")
    public void updateMate_Success() {
        
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