package com.hamryt.helparty.service.user;

import com.hamryt.helparty.dto.user.UserDTO;
import com.hamryt.helparty.dto.user.request.SignUpUserRequest;
import com.hamryt.helparty.dto.user.request.UpdateUserReqeust;
import com.hamryt.helparty.dto.user.request.UserDeleteRequest;
import com.hamryt.helparty.dto.user.response.SignUpUserResponse;
import com.hamryt.helparty.dto.user.response.UpdateUserResponse;
import com.hamryt.helparty.exception.user.EmailExistedException;
import com.hamryt.helparty.exception.user.InsertUserFailedException;
import com.hamryt.helparty.exception.user.UpdateFailedException;
import com.hamryt.helparty.exception.user.UserDeleteFailedException;
import com.hamryt.helparty.exception.user.UserNotFoundByIdException;
import com.hamryt.helparty.exception.user.UserNotFoundException;
import com.hamryt.helparty.mapper.UserMapper;
import com.hamryt.helparty.service.login.Encryptor;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final Encryptor encryptor;

    @Transactional
    public SignUpUserResponse insertUser(SignUpUserRequest signupUserRequest) {

        if (isExistsEmail(signupUserRequest.getEmail())) {
            throw new EmailExistedException(signupUserRequest.getEmail());
        }

        String encodedPassword = encryptor.encrypt(signupUserRequest.getPassword());

        UserDTO newUser = UserDTO.builder()
            .email(signupUserRequest.getEmail())
            .name(signupUserRequest.getName())
            .password(encodedPassword)
            .phoneNumber(signupUserRequest.getPhoneNumber())
            .addressCode(signupUserRequest.getAddressCode())
            .addressDetail(signupUserRequest.getAddressDetail())
            .build();

        if (userMapper.insertUser(newUser) != 1) {
            throw new InsertUserFailedException(newUser.toString());
        }

        return SignUpUserResponse.of(newUser);
    }

    @Transactional
    public UpdateUserResponse updateUser(Long id, UpdateUserReqeust updateUserReqeust) {

        String encodedPassword = encryptor.encrypt(updateUserReqeust.getPassword());

        UpdateUserResponse updateUserResponse = UpdateUserResponse
            .of(id, encodedPassword, updateUserReqeust);

        if (userMapper.updateUser(updateUserResponse) != 1) {
            throw new UpdateFailedException(updateUserReqeust.toString());
        }

        return updateUserResponse;
    }

    @Transactional
    public void deleteUser( UserDeleteRequest userDeleteRequest) {
        String email = userDeleteRequest.getEmail();
        String encodedPassword = encryptor.encrypt(userDeleteRequest.getPassword());

        if(userMapper.deleteUserByEmailAndPassword(email, encodedPassword) != 1){
            throw new UserDeleteFailedException(email);
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        UserDTO user = userMapper.findUserById(id);
        if (user == null) {
            throw new UserNotFoundByIdException(id);
        }
        return user;
    }

    @Transactional(readOnly = true)
    public boolean isExistsEmail(String email) {
        return userMapper.isExistsEmail(email);

    }

    @Transactional(readOnly = true)
    public UserDTO findUserByEmail(String email) {
        return Optional.ofNullable(userMapper.findUserByEmail(email))
            .orElseThrow(()->new UserNotFoundException(email));
    }

    @Transactional(readOnly = true)
    public UserDTO findUserByEmailAndPassword(String email, String password) {
        return userMapper.findUserByEmailAndPassword(email, password);
    }

}