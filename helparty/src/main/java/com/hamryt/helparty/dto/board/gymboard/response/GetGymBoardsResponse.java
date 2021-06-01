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
    
    public static GetGymBoardsResponse of(List<GetGymBoardResponse> paramGymBoardList, int page,
        int size) {
        List<GetGymBoardResponse> gymBoardList = null;
        
        if (paramGymBoardList.size() != 0) {
            gymBoardList = paramGymBoardList;
        }
        
        return GetGymBoardsResponse.builder()
            .getGymBoardResponseList(gymBoardList)
            .page(page)
            .size(size)
            .build();
    }
}
