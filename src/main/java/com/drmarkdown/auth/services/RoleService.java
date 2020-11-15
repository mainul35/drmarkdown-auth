package com.drmarkdown.auth.services;

import com.drmarkdown.auth.dtos.RoleDto;

public interface RoleService {

    void createRole(RoleDto roleDto);

    RoleDto roleInfo(String roleId);
}
