package com.hamryt.helparty.service.gym;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse;
import com.hamryt.helparty.exception.gym.GymDeleteFailedException;
import com.hamryt.helparty.exception.gym.GymNotFoundByIdException;
import com.hamryt.helparty.exception.gym.GymNotFoundException;
import com.hamryt.helparty.exception.gym.InsertGymFailedExcetpion;
import com.hamryt.helparty.exception.user.DoesNotMatchUserType;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.exception.user.UpdateFailedException;
import com.hamryt.helparty.mapper.GymMapper;
import com.hamryt.helparty.service.session.Encryptor;
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
    
    @Transactional
    public UpdateGymResponse updateGym(Long id, UpdateGymRequest updateGymRequest) {
        
        String encodedPassword = encryptor.encrypt(updateGymRequest.getPassword());
        
        UpdateGymResponse updateGymResponse = UpdateGymResponse
            .of(id, encodedPassword, updateGymRequest);
        
        if (gymMapper.updateGym(updateGymResponse) != 1) {
            throw new UpdateFailedException(id);
        }
        return updateGymResponse;
    }
    
    @Transactional(readOnly = true)
    public String findGymEmailById(Long id) {
        return Optional.ofNullable(gymMapper.findGymEmailById(id))
            .orElseThrow(() -> new GymNotFoundByIdException(id));
    }
    
    @Transactional
    public void deleteGym(Long id) {
        if (gymMapper.deleteGymById(id) != 1) {
            throw new GymDeleteFailedException(id);
        }
    }
    
    @Transactional(readOnly = true)
    public boolean isExistsEmail(String email) {
        return gymMapper.isExistsEmail(email);
    }
}
