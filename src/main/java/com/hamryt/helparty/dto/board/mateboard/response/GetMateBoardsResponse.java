package com.hamryt.helparty.dto.board.mateboard.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMateBoardsResponse {
    
    private List<GetMateBoardResponse> mateBoardList;
    
    private int page;
    
    private int size;
    
    @Builder
    public GetMateBoardsResponse(List<GetMateBoardResponse> mateBoardList, int page, int size) {
        this.mateBoardList = mateBoardList;
        this.page = page;
        this.size = size;
    }
}
