package com.hamryt.helparty.service.login;



import com.hamryt.helparty.dto.login.LoginDTO;
import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.exception.user.UserNotFoundException;
import com.hamryt.helparty.service.session.Encryptor;
import com.hamryt.helparty.service.user.UserService;
import com.hamryt.helparty.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLoginServiceImpl implements LoginService {
    
    private final Encryptor encryptor;
    private final HttpSession session;
    private final UserService userService;
    
    @Transactional(readOnly = true)
    public LoginDTO login(String email, String password) {
        
        String encryptPassword = encryptor.encrypt(password);
        UserDTO userDTO = userService.findUserByEmailAndPassword(email, encryptPassword);
        
        if (userDTO != null) {
            session.setAttribute(SessionKeys.LOGIN_USER_ID, userDTO.getId());
        } else {
            throw new UserNotFoundException(email);
        }
        
        return LoginDTO.builder()
            .id(userDTO.getId())
            .name(userDTO.getName())
            .userType(userDTO.getUserType())
            .build();
    }
}
