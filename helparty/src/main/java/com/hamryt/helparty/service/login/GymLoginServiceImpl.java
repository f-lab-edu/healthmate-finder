package com.hamryt.helparty.service.login;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.exception.common.UserTypeDoesNotMatchException;
import com.hamryt.helparty.service.gym.GymService;
import com.hamryt.helparty.service.session.Encryptor;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GymLoginServiceImpl implements LoginService<GymDTO> {
    
    private final Encryptor encryptor;
    private final HttpSession session;
    private final GymService gymService;
    
    @Transactional(readOnly = true)
    public GymDTO login(String email, String password) {
        
        String encryptPassword = encryptor.encrypt(password);
        GymDTO gymDTO = gymService.findGymByEmailAndPassword(email, encryptPassword);
        
        if (gymDTO.getUserType() != UserType.GYM) {
            throw new UserTypeDoesNotMatchException(UserType.GYM);
        }
        
        if (gymDTO != null) {
            session.setAttribute(SessionKeys.LOGIN_GYM_EMAIL, gymDTO.getEmail());
        }
        
        return gymDTO;
    }
    
}
