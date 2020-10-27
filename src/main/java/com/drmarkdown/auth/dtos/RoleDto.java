package com.drmarkdown.auth.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleDto {
    private String role;
    private String createdAt;
    private String modifiedAt;
}
