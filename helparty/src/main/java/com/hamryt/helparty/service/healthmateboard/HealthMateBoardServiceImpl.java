package com.hamryt.helparty.service.healthmateboard;

import com.hamryt.helparty.dto.healthmateboard.HealthMateBoardDTO;
import com.hamryt.helparty.dto.healthmateboard.request.CreateHealthMateBoardRequest;
import com.hamryt.helparty.dto.healthmateboard.response.CreateHealthMateBoardResponse;
import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.exception.healmateboard.InsertHealthMateBoardFailedException;
import com.hamryt.helparty.mapper.healthmateboard.HealthMateBoardMapper;
import com.hamryt.helparty.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HealthMateBoardServiceImpl implements HealthMateBoardService{

    private final UserService userService;
    private final HealthMateBoardMapper healthMateBoardMapper;

    @Transactional
    public CreateHealthMateBoardResponse insertHealthMateBoard(
        CreateHealthMateBoardRequest createHealthMateBoardRequest,
        String email
    ) {
        UserDTO user = userService.findUserByEmail(email);

        HealthMateBoardDTO healthMateBoard
            = HealthMateBoardDTO.builder()
                .userEmail(email)
                .user(user)
                .content(createHealthMateBoardRequest.getContent())
                .startTime(createHealthMateBoardRequest.getStartTime())
                .endTime(createHealthMateBoardRequest.getEndTime())
                .build();

        if(healthMateBoardMapper.insertHealthMateBoard(healthMateBoard) != 1){
            throw new InsertHealthMateBoardFailedException(healthMateBoard.toString());
        }
        return CreateHealthMateBoardResponse.of(healthMateBoard);
    }

}
