package com.hamryt.helparty.service.gym;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.hamryt.helparty.dto.UserType;
import com.hamryt.helparty.dto.gym.GymDTO;
import com.hamryt.helparty.dto.gym.request.SignUpGymRequest;
import com.hamryt.helparty.dto.gym.request.UpdateGymRequest;
import com.hamryt.helparty.dto.gym.response.SignUpGymResponse;
import com.hamryt.helparty.dto.gym.response.UpdateGymResponse;
import com.hamryt.helparty.exception.gym.GymDeleteFailedException;
import com.hamryt.helparty.exception.gym.GymNotFoundException;
import com.hamryt.helparty.exception.gym.InsertGymFailedExcetpion;
import com.hamryt.helparty.exception.user.DoesNotMatchUserType;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.exception.user.UpdateFailedException;
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
    
    String email = "test@example.com";
    String gymName = "test";
    String password = "123";
    String phoneNumber = "01012345678";
    String addressCode = "1234";
    String addressDetail = "seoul";
    UserType userTypeGym = UserType.GYM;
    UserType userTypeUser = UserType.USER;
    
    SignUpGymRequest signUpGymRequestGym = SignUpGymRequest.builder()
        .email(email)
        .gymName(gymName)
        .password(password)
        .phoneNumber(phoneNumber)
        .addressCode(addressCode)
        .addressDetail(addressDetail)
        .userType(userTypeGym)
        .build();
    
    SignUpGymRequest signUpGymRequestUser = SignUpGymRequest.builder()
        .email(email)
        .gymName(gymName)
        .password(password)
        .phoneNumber(phoneNumber)
        .addressCode(addressCode)
        .addressDetail(addressDetail)
        .userType(userTypeUser)
        .build();
    
    UpdateGymRequest updateGymRequest = UpdateGymRequest.builder()
        .gymName(gymName)
        .password(password)
        .phoneNumber(phoneNumber)
        .addressCode(addressCode)
        .addressDetail(addressDetail)
        .userType(userTypeGym)
        .build();
    
    @Test
    @DisplayName("운동 시설 관리자 계정정보 수정 성공")
    public void updateGym_Success() {
        
        given(gymMapper.updateGym(any())).willReturn(1);
        
        UpdateGymResponse updateGymResponse = gymService.updateGym(1004L, updateGymRequest);
        
        assertEquals(updateGymResponse.getGymName(), gymName);
    }
    
    @Test
    @DisplayName("운동 시설 관리자 계정정보 수정 실패 : 수정 쿼리 실패")
    public void updateGym_Fail() {
        
        given(gymMapper.updateGym(any())).willReturn(2);
        
        UpdateFailedException updateFailedException
            = assertThrows(UpdateFailedException.class,
            () -> gymService.updateGym(1004L, updateGymRequest));
        
        assertEquals("Update did not happened with this model : " + gymName,
            updateFailedException.getMessage());
    }
    
    @Test
    @DisplayName("운동 시설 관리자 회원가입 성공")
    public void createGym_Success() {
        
        given(gymMapper.isExistsEmail(any())).willReturn(false);
        given(gymMapper.insertGym(any())).willReturn(1);
        
        SignUpGymResponse signUpGymResponse = gymService.insertGym(signUpGymRequestGym);
        
        assertEquals(signUpGymResponse.getGymName(), gymName);
        
    }
    
    @Test
    @DisplayName("운동 시설 관리자 회원 가입 실패 : 회원 타입 불일치")
    public void createGym_Fail_DoesNotMatchUserType() {
        
        DoesNotMatchUserType doesNotMatchUserType
            = assertThrows(DoesNotMatchUserType.class,
            () -> gymService.insertGym(signUpGymRequestUser));
        
        assertEquals("UserType does not match. It Must be : GYM",
            doesNotMatchUserType.getMessage());
    }
    
    @Test
    @DisplayName("운동 시설 관리자 회원 가입 실패 : 이미 존재하는 이메일")
    public void createGym_Fail_EmailExistedException() {
        
        given(gymMapper.isExistsEmail(any())).willReturn(true);
        
        EmailExistedException emailExistedException
            = assertThrows(EmailExistedException.class,
            () -> gymService.insertGym(signUpGymRequestGym));
        
        assertEquals("Email is already registered: test@example.com",
            emailExistedException.getMessage());
    }
    
    @Test
    @DisplayName("운동 시설 관리자 회원 가입 실패 : 삽입 쿼리 실패")
    public void createGym_Fail_InsertGymFailException() {
        
        given(gymMapper.isExistsEmail(any())).willReturn(false);
        given(gymMapper.insertGym(any())).willReturn(0);
        
        InsertGymFailedExcetpion insertGymFailedExcetpion
            = assertThrows(InsertGymFailedExcetpion.class,
            () -> gymService.insertGym(signUpGymRequestGym));
        
        assertEquals("Insert Gym failed exception",
            insertGymFailedExcetpion.getMessage());
    }
    
    @Test
    @DisplayName("Eamil과 Password로 GymDTO 조회 성공")
    public void findGymByEmailAndPassword_Success() {
        
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
    
    @Test
    @DisplayName("운동 시설 관리자 계정 삭제 성공")
    public void deleteGym_Success() {
        given(gymMapper.deleteGymById(eq(1004L))).willReturn(1);
        
        gymService.deleteGym(1004L);
        
        verify(gymMapper).deleteGymById(1004L);
    }
    
    @Test
    @DisplayName("운동 시설 관리자 계정 삭제 실패 : 삭제 쿼리 실패")
    public void deleteGym_Fail() {
        given(gymMapper.deleteGymById(eq(1004L))).willReturn(0);
        
        GymDeleteFailedException gymDeleteFailedException
            = assertThrows(GymDeleteFailedException.class,
            () -> gymService.deleteGym(1004L));
        
        assertEquals("Delete Gym by ID Failed : 1004",
            gymDeleteFailedException.getMessage());
    }
    
}