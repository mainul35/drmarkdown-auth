package com.drmarkdown.auth.services;

import com.drmarkdown.auth.dtos.UserInfoDto;
import com.drmarkdown.auth.dtos.UserLoginDto;

public interface UserService {

    void createUser(UserInfoDto userInfoDto);

    UserInfoDto retrieveUserInfo(String userId);

    UserInfoDto loginUser(UserLoginDto userLoginDto);
}
