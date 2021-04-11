package com.hamryt.helparty.dto.mateboard.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMatesBoardResponse {
    
    private List<GetMateBoardResponse> mateBoardList;
    
    private int page;
    
    private int size;
    
    @Builder
    public GetMatesBoardResponse(List<GetMateBoardResponse> mateBoardList, int page, int size){
        this.mateBoardList = mateBoardList;
        this.page = page;
        this.size = size;
    }

}
