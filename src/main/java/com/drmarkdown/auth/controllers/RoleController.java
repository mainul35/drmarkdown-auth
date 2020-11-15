package com.drmarkdown.auth.controllers;

import com.drmarkdown.auth.dtos.RoleDto;
import com.drmarkdown.auth.services.RoleService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        Preconditions.checkNotNull(roleDto);
        roleService.createRole(roleDto);
        return roleDto;
    }

    @GetMapping("/info/{roleId}")
    public RoleDto getRoleInfo(@PathVariable String roleId) {
        return roleService.roleInfo(roleId);
    }

    // TODO: delete role

    // TODO: update role
}
