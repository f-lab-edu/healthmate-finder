package com.hamryt.helparty.mapper;

import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse;

public interface GymMapper {
    
    int insertGym(GymDTO newGym);
    
    boolean isExistsEmail(String email);
    
    GymDTO findGymByEmailAndPassword(String email, String password);
    
    int updateGym(UpdateGymResponse updateGymResponse);
    
    String findGymEmailById(Long id);
}
