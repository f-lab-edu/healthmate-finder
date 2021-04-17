package com.hamryt.helparty.service.mateboard;

import com.hamryt.helparty.dto.mateboard.MateBoardDTO;
import com.hamryt.helparty.dto.mateboard.request.CreateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.request.UpdateMateBoardRequest;
import com.hamryt.helparty.dto.mateboard.response.CreateMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.GetMateBoardResponse;
import com.hamryt.helparty.dto.mateboard.response.UpdateMateBoardResponse;
import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.exception.mateboard.InsertMateBoardFailedException;
import com.hamryt.helparty.exception.user.UpdateFailedException;
import com.hamryt.helparty.mapper.MateBoardMapper;
import com.hamryt.helparty.service.user.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MateBoardServiceImpl implements MateBoardService {
    
    private final UserService userService;
    private final MateBoardMapper mateBoardMapper;
    
    @Transactional
    public CreateMateBoardResponse addMateBoard(
        CreateMateBoardRequest createMateBoardRequest,
        String email
    ) {
        UserDTO user = userService.findUserByEmail(email);
        
        MateBoardDTO mateBoard
            = MateBoardDTO.builder()
            .gym(createMateBoardRequest.getGym())
            .content(createMateBoardRequest.getContent())
            .startTime(createMateBoardRequest.getStartTime())
            .endTime(createMateBoardRequest.getEndTime())
            .userId(user.getId())
            .user(user)
            .build();
        
        if (mateBoardMapper.insertMateBoard(mateBoard) != 1) {
            throw new InsertMateBoardFailedException(mateBoard.toString());
        }
        return CreateMateBoardResponse.of(mateBoard);
    }
    
    @Transactional(readOnly = true)
    @Cacheable(value = "matebaords")
    public List<GetMateBoardResponse> getMates(int page, int size) {
        return mateBoardMapper.findMateBoardByPage(page * size, size);
    }
    
    @Transactional(readOnly = true)
    @Cacheable(value = "matebaords")
    public GetMateBoardResponse getMate(Long id){
        return mateBoardMapper.findMateBoardById(id);
    }
    
    @Transactional
    public UpdateMateBoardResponse updateMateBoard(
        Long id,
        UpdateMateBoardRequest updateMateBoardRequest
    ) {
        UpdateMateBoardResponse updateMateBoardResponse = UpdateMateBoardResponse
            .of(id, updateMateBoardRequest);
        
        if (mateBoardMapper.updateMateBoard(updateMateBoardResponse) != 1){
            throw new UpdateFailedException(updateMateBoardRequest.toString());
        }
        
        return updateMateBoardResponse;
    }
    
}
