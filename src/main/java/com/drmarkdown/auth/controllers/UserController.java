package com.drmarkdown.auth.controllers;

import com.drmarkdown.auth.dtos.UserInfoDto;
import com.drmarkdown.auth.dtos.UserLoginDto;
import com.drmarkdown.auth.services.UserService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserInfoDto createUser(@RequestBody UserInfoDto userInfoDto) {
        Preconditions.checkNotNull(userInfoDto);
        userService.createUser(userInfoDto);
        return userInfoDto;
    }

    @GetMapping("/info/{userId}")
    public UserInfoDto getUserInfo(@PathVariable String userId) {
        return userService.retrieveUserInfo(userId);
    }

    @PostMapping("/login")
    public UserInfoDto login(@RequestBody UserLoginDto userLoginDto) {
        Preconditions.checkNotNull(userLoginDto);
        return userService.loginUser(userLoginDto);
    }
    // TODO: Update user

    // TODO: Delete user
}
