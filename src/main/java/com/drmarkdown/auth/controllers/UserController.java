package com.drmarkdown.auth.controllers;

import com.drmarkdown.auth.dtos.UserInfoDto;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
