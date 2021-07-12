package com.hamryt.helparty.dto.gym;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleGymInfo {
    
    private String gymName;
    private String phoneNumber;
    private String addressDetail;
    private String addressCode;
    
    @Builder
    public SimpleGymInfo(String gymName, String phoneNumber, String addressDetail,
        String addressCode) {
        this.gymName = gymName;
        this.phoneNumber = phoneNumber;
        this.addressDetail = addressDetail;
        this.addressCode = addressCode;
    }
    
}
