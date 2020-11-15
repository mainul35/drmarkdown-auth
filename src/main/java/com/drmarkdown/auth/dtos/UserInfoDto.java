package com.drmarkdown.auth.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoDto {
    private String id;
    private String username;
    private String displayName;
    private List<String> roles;
    private String email;
    private String password;
    private Date createdAt;
    private Date modifiedAt;
}
