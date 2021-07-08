package com.hamryt.helparty.service.login;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.login.LoginDTO;
import com.hamryt.helparty.service.gym.GymServiceImpl;
import com.hamryt.helparty.service.session.Encryptor;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GymLoginServiceImplTest {
    
    @InjectMocks
    private GymLoginServiceImpl loginService;
    
    @Mock
    private GymServiceImpl gymService;
    
    @Mock
    private Encryptor encryptor;
    
    @Mock
    private HttpSession session;
    
    String email = "test@example.com";
    String password = "123";
    
    @Test
    @DisplayName("운동시설 관리자 로그인 성공")
    public void loginGym_Success() {
        
        String encryptedPassword = encryptor.encrypt(password);
        String gymName = "test";
        String phoneNumber = "01012345678";
        String addressCode = "0123";
        String addressDetail = "seoul";
        UserType userType = UserType.GYM;
        
        GymDTO mockGym = GymDTO.create(null, email, gymName, encryptedPassword, phoneNumber, addressCode, addressDetail, userType);
        
        given(gymService.findGymByEmailAndPassword(eq(email), eq(encryptedPassword)))
            .willReturn(mockGym);
        
        LoginDTO loginDTO = loginService.login(email, password);
        
        assertEquals(loginDTO.getName(), gymName);
    }
    
}