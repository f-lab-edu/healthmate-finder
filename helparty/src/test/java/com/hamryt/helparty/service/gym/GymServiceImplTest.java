package com.hamryt.helparty.service.gym;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.exception.gym.GymNotFoundException;
import com.hamryt.helparty.exception.gym.InsertGymFailedExcetpion;
import com.hamryt.helparty.exception.user.DoesNotMatchUserType;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.mapper.GymMapper;
import com.hamryt.helparty.service.session.Encryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GymServiceImplTest {
    
    @InjectMocks
    private GymServiceImpl gymService;
    
    @Mock
    private GymMapper gymMapper;
    
    @Mock
    private Encryptor encryptor;
    
    @Test
    @DisplayName("운동 시설 업주 회원가입 성공")
    public void createGym_Success() {
        
        String email = "test@example.com";
        String gymName = "test";
        String password = "123";
        String phoneNumber = "01012345678";
        String addressCode = "1234";
        String addressDetail = "seoul";
        UserType userType = UserType.GYM;
        
        SignUpGymRequest signUpGymRequest = SignUpGymRequest.builder()
            .email(email)
            .gymName(gymName)
            .password(password)
            .phoneNumber(phoneNumber)
            .addressCode(addressCode)
            .addressDetail(addressDetail)
            .userType(userType)
            .build();
        
        given(gymMapper.isExistsEmail(any())).willReturn(false);
        
        given(gymMapper.insertGym(any())).willReturn(1);
        
        SignUpGymResponse signUpGymResponse = gymService.insertGym(signUpGymRequest);
        
        assertEquals(signUpGymResponse.getGymName(), gymName);
        
    }
    
    @Test
    @DisplayName("운동 시설 업주 회원 가입 실패 : 회원 타입 불일치")
    public void createGym_Fail_DoesNotMatchUserType() {
        
        String email = "test@example.com";
        String gymName = "test";
        String password = "123";
        String phoneNumber = "01012345678";
        String addressCode = "1234";
        String addressDetail = "seoul";
        UserType userType = UserType.USER;
        
        SignUpGymRequest signUpGymRequest = SignUpGymRequest.builder()
            .email(email)
            .gymName(gymName)
            .password(password)
            .phoneNumber(phoneNumber)
            .addressCode(addressCode)
            .addressDetail(addressDetail)
            .userType(userType)
            .build();
        
        DoesNotMatchUserType doesNotMatchUserType
            = assertThrows(DoesNotMatchUserType.class,
            () -> gymService.insertGym(signUpGymRequest));
        
        assertEquals("UserType does not match. It Must be : GYM",
            doesNotMatchUserType.getMessage());
    }
    
    @Test
    @DisplayName("운동 시설 업주 회원 가입 실패 : 이미 존재하는 이메일")
    public void createGym_Fail_EmailExistedException() {
        
        String email = "test@example.com";
        String gymName = "test";
        String password = "123";
        String phoneNumber = "01012345678";
        String addressCode = "1234";
        String addressDetail = "seoul";
        UserType userType = UserType.GYM;
        
        SignUpGymRequest signUpGymRequest = SignUpGymRequest.builder()
            .email(email)
            .gymName(gymName)
            .password(password)
            .phoneNumber(phoneNumber)
            .addressCode(addressCode)
            .addressDetail(addressDetail)
            .userType(userType)
            .build();
        
        given(gymMapper.isExistsEmail(any())).willReturn(true);
        
        EmailExistedException emailExistedException
            = assertThrows(EmailExistedException.class,
            () -> gymService.insertGym(signUpGymRequest));
        
        assertEquals("Email is already registered: test@example.com",
            emailExistedException.getMessage());
    }
    
    @Test
    @DisplayName("운동 시설 업주 회원 가입 실패 : 삽입 쿼리 실패")
    public void createGym_Fail_InsertGymFailException() {
        String email = "test@example.com";
        String gymName = "test";
        String password = "123";
        String phoneNumber = "01012345678";
        String addressCode = "1234";
        String addressDetail = "seoul";
        UserType userType = UserType.GYM;
        
        SignUpGymRequest signUpGymRequest = SignUpGymRequest.builder()
            .email(email)
            .gymName(gymName)
            .password(password)
            .phoneNumber(phoneNumber)
            .addressCode(addressCode)
            .addressDetail(addressDetail)
            .userType(userType)
            .build();
        
        given(gymMapper.isExistsEmail(any())).willReturn(false);
        given(gymMapper.insertGym(any())).willReturn(0);
        
        InsertGymFailedExcetpion insertGymFailedExcetpion
            = assertThrows(InsertGymFailedExcetpion.class,
            () -> gymService.insertGym(signUpGymRequest));
        
        assertEquals("Insert Gym failed exception",
            insertGymFailedExcetpion.getMessage());
    }
    
    @Test
    @DisplayName("Eamil과 Password로 GymDTO 조회 성공")
    public void findGymByEmailAndPassword_Success() {
        String email = "test@example.com";
        String password = "test";
        String gymName = "test";
        GymDTO MockGym = GymDTO.builder()
            .email(email)
            .gymName(gymName)
            .password(password)
            .phoneNumber("01012345678")
            .addressCode("0123")
            .addressDetail("seoul")
            .userType(UserType.GYM)
            .build();
        
        given(gymMapper.findGymByEmailAndPassword(eq(email), eq(password))).willReturn(MockGym);
        
        GymDTO gymDTO = gymService.findGymByEmailAndPassword(email, password);
        
        assertEquals(gymDTO.getGymName(), gymName);
    }
    
    @Test
    @DisplayName("Email과 Password로 GymDTO 조회 실패 : 해당 운동시설 관리자 계정 없음 예외")
    public void findGymByEmailAndPassword_Fail() {
        String email = "test@example.com";
        String password = "test";
        
        given(gymMapper.findGymByEmailAndPassword(any(), any())).willReturn(null);
        
        GymNotFoundException gymNotFoundException
            = assertThrows(GymNotFoundException.class,
            () -> gymService.findGymByEmailAndPassword(email, password));
        
        assertEquals("This gym does not exists with this email : test@example.com",
            gymNotFoundException.getMessage());
    }
    
}