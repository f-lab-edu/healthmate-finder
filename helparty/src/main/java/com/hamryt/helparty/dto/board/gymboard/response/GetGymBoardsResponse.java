package com.hamryt.helparty.dto.board.gymboard.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGymBoardsResponse {
    
    private List<GetGymBoardResponse> getGymBoardResponseList;
    private int page;
    private int size;
    
    @Builder
    public GetGymBoardsResponse(
        List<GetGymBoardResponse> getGymBoardResponseList,
        int page, int size
    ) {
        this.getGymBoardResponseList = getGymBoardResponseList;
        this.page = page;
        this.size = size;
    }
}
