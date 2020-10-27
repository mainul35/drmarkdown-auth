package com.drmarkdown.auth.controllers;

import com.drmarkdown.auth.dtos.RoleDto;
import com.drmarkdown.auth.dtos.UserInfoDto;
import com.google.common.base.Preconditions;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @PostMapping("/create")
    public RoleDto createUser(@RequestBody RoleDto roleDto) {

        Preconditions.checkNotNull(roleDto);

        System.out.println(roleDto.toString());
        return roleDto;
    }

    @GetMapping("/info/{roleId}")
    public UserInfoDto getUserInfo(@PathVariable String roleId) {
        System.out.println(roleId);
        return null;
    }

    // TODO: delete role

    // TODO: update role
}
