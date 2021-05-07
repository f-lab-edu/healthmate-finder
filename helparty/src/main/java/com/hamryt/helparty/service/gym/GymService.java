package com.hamryt.helparty.service.gym;

import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;

public interface GymService {
    
    SignUpGymResponse insertGym(SignUpGymRequest resource);
    
    GymDTO findGymByEmailAndPassword(String email, String encryptPassword);
}
