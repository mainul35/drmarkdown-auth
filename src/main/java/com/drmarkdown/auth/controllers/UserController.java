package com.drmarkdown.auth.controllers;

import com.drmarkdown.auth.dtos.UserInfoDto;
import com.drmarkdown.auth.dtos.UserLoginDto;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/create")
    public UserInfoDto createUser(@RequestBody UserInfoDto userInfoDto) {

        Preconditions.checkNotNull(userInfoDto);

        System.out.println(userInfoDto.toString());
        return userInfoDto;
    }

    @GetMapping("/info/{userId}")
    public UserInfoDto getUserInfo(@PathVariable String userId) {
        System.out.println(userId);
        return null;
    }

    @PostMapping("/login")
    public UserLoginDto login(@RequestBody UserLoginDto userLoginDto) {

        Preconditions.checkNotNull(userLoginDto);
        System.out.println(userLoginDto.toString());
        return userLoginDto;
    }
    // TODO: Update user

    // TODO: Delete user
}
