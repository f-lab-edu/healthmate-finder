package com.hamryt.helparty.service.session.factory;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.exception.login.LoginUserDoesNotMatchException;
import com.hamryt.helparty.service.gym.GymService;
import com.hamryt.helparty.service.user.UserService;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserSessionValidateFactory extends UserSessionFactory {
    
    private final UserService userService;
    private final GymService gymService;
    private final HttpSession session;
    
    @Transactional(readOnly = true)
    public void createValidateUser(Long id, UserType userType) {
        String userEmail = (String) session.getAttribute(SessionKeys.LOGIN_USER_ID);
        String checkEmail = null;
        
        switch (userType) {
            case USER:
                checkEmail = userService.findUserEmailById(id);
                break;
            case GYM:
                checkEmail = gymService.findGymEmailById(id);
                break;
            default:
                break;
        }
        
        if (!checkEmail.equals(userEmail)) {
            throw new LoginUserDoesNotMatchException(userEmail);
        }
    }
    
}
