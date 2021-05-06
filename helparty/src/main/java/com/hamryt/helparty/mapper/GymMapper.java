package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.gym.GymDTO;

public interface GymMapper {
    
    int insertGym(GymDTO newGym);
    
    boolean isExistsEmail(String email);
}
