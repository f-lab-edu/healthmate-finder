package com.hamryt.helparty.service.login;

import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.login.LoginDTO;
import com.hamryt.helparty.service.gym.GymService;
import com.hamryt.helparty.service.session.Encryptor;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GymLoginServiceImpl implements LoginService {
    
    private final Encryptor encryptor;
    private final HttpSession session;
    private final GymService gymService;
    
    @Transactional(readOnly = true)
    public LoginDTO login(String email, String password) {
        
        String encryptPassword = encryptor.encrypt(password);
        GymDTO gymDTO = gymService.findGymByEmailAndPassword(email, encryptPassword);
        
        if (gymDTO != null) {
            session.setAttribute(SessionKeys.LOGIN_USER_EMAIL, gymDTO.getEmail());
        }
        
        return LoginDTO.builder()
            .id(gymDTO.getId())
            .name(gymDTO.getGymName())
            .userType(gymDTO.getUserType())
            .build();
    }
}
