package com.hamryt.helparty.service.gym;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.exception.gym.GymNotFoundException;
import com.hamryt.helparty.exception.gym.InsertGymFailedExcetpion;
import com.hamryt.helparty.exception.user.DoesNotMatchUserType;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.mapper.GymMapper;
import com.hamryt.helparty.service.login.Encryptor;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GymServiceImpl implements GymService {
    
    private final Encryptor encryptor;
    private final GymMapper gymMapper;
    
    @Transactional
    public SignUpGymResponse insertGym(SignUpGymRequest signUpGymRequest) {
        
        if (signUpGymRequest.getUserType() != UserType.GYM) {
            throw new DoesNotMatchUserType(UserType.GYM);
        }
        
        if (isExistsEmail(signUpGymRequest.getEmail())) {
            throw new EmailExistedException(signUpGymRequest.getEmail());
        }
        
        String encodedpassword = encryptor.encrypt(signUpGymRequest.getPassword());
        
        GymDTO newGym = GymDTO.builder()
            .email(signUpGymRequest.getEmail())
            .gymName(signUpGymRequest.getGymName())
            .password(encodedpassword)
            .phoneNumber(signUpGymRequest.getPhoneNumber())
            .addressCode(signUpGymRequest.getAddressCode())
            .addressDetail(signUpGymRequest.getAddressDetail())
            .userType(signUpGymRequest.getUserType())
            .build();
        
        if (gymMapper.insertGym(newGym) != 1) {
            throw new InsertGymFailedExcetpion();
        }
        
        return SignUpGymResponse.of(newGym);
    }
    
    @Transactional(readOnly = true)
    public GymDTO findGymByEmailAndPassword(String email, String password) {
        return Optional.ofNullable(gymMapper.findGymByEmailAndPassword(email, password))
            .orElseThrow(() -> new GymNotFoundException(email));
    }
    
    @Transactional(readOnly = true)
    public boolean isExistsEmail(String email) {
        return gymMapper.isExistsEmail(email);
    }
}
