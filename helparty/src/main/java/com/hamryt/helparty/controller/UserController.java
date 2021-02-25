package com.hamryt.helparty.controller;

import com.hamryt.helparty.dto.UserDto;
import com.hamryt.helparty.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping
    public void registerUser(
        @Valid @RequestBody UserDto resource
    ) {

        userService.insertUser(resource);
    }


}
