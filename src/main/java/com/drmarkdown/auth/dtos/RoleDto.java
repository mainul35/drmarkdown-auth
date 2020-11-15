package com.drmarkdown.auth.dtos;

import lombok.Data;

@Data
public class RoleDto {
    private String id;
    private String role;
    private String createdAt;
    private String modifiedAt;
}
