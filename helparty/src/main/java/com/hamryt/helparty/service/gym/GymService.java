package com.hamryt.helparty.service.gym;

import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse;

public interface GymService {
    
    SignUpGymResponse insertGym(SignUpGymRequest resource);
    
    GymDTO findGymByEmailAndPassword(String email, String encryptPassword);
    
    UpdateGymResponse updateGym(Long loginId, UpdateGymRequest updateGymRequest);
    
    String findGymEmailById(Long id);
    
    void deleteGym(Long loginId);
}
