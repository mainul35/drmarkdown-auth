package com.drmarkdown.auth.services.impl;

import com.drmarkdown.auth.dtos.RoleDto;
import com.drmarkdown.auth.models.MarkdownRoleModel;
import com.drmarkdown.auth.repositories.RoleRepository;
import com.drmarkdown.auth.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createRole(RoleDto roleDto) {
        MarkdownRoleModel markdownRoleModel = new MarkdownRoleModel(roleDto);
        roleRepository.save(markdownRoleModel);
        modelMapper.map(markdownRoleModel, roleDto);
    }

    @Override
    public RoleDto roleInfo(String roleId) {
        Optional<MarkdownRoleModel> optionalMarkdownRoleModel = roleRepository.findById(roleId);
        if (optionalMarkdownRoleModel.isPresent()) {
            final MarkdownRoleModel markdownRoleModel = optionalMarkdownRoleModel.get();
            return modelMapper.map(markdownRoleModel, RoleDto.class);
        }
        return null;
    }
}
