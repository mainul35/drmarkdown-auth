package com.drmarkdown.auth.initialize;

import com.drmarkdown.auth.models.MarkdownRoleModel;
import com.drmarkdown.auth.models.MarkdownUserModel;
import com.drmarkdown.auth.repositories.RoleRepository;
import com.drmarkdown.auth.repositories.UserRepository;
import com.drmarkdown.auth.services.TokenService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Profile({"dev", "test"})
@Component
public class InitializeTestData implements InitializeData {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final ResourceLoader resourceLoader;

    public InitializeTestData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TokenService tokenService, ResourceLoader resourceLoader) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void initialize() {
        addRoles();
        addUsers();
    }

    private void addRoles() {
        roleRepository.deleteAll();

        try {
            List<MarkdownRoleModel> markdownRoleModels = new ObjectMapper()
                    .readValue(
                            resourceLoader.getResource("classpath:roles.json").getInputStream(),
                            new TypeReference<ArrayList<MarkdownRoleModel>>() {
                            }
                    );
            markdownRoleModels.forEach(markdownRoleModel -> {
                roleRepository.saveAndFlush(markdownRoleModel);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUsers() {
        userRepository.deleteAll();
        try {
            List<MarkdownUserModel> markdownUserModels = new ObjectMapper()
                    .readValue(
                            resourceLoader.getResource("classpath:users.json").getInputStream(),
                            new TypeReference<ArrayList<MarkdownUserModel>>() {
                            }
                    );
            markdownUserModels.forEach(markdownUserModel -> {
                tokenService.generateToken(markdownUserModel);
                markdownUserModel.setPassword(passwordEncoder.encode(markdownUserModel.getPassword()));
                userRepository.saveAndFlush(markdownUserModel);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
