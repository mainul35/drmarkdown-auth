package com.drmarkdown.auth.initialize;

import com.drmarkdown.auth.models.MarkdownRoleModel;
import com.drmarkdown.auth.models.MarkdownUserModel;
import com.drmarkdown.auth.repositories.RoleRepository;
import com.drmarkdown.auth.repositories.UserRepository;
import com.drmarkdown.auth.services.TokenService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Profile("prod")
@Component
public class InitializeProdData implements InitializeData {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    public InitializeProdData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public void initialize() {
        addRoles();
        addUsers();
    }

    private void addUsers() {
        try {
            List<MarkdownUserModel> markdownUserModels = new ObjectMapper()
                    .readValue(
                            new ClassPathResource("users.json").getFile(),
                            new TypeReference<ArrayList<MarkdownUserModel>>() {
                            }
                    );
            markdownUserModels.forEach(markdownUserModel -> {
                boolean found = userRepository
                        .findByUsernameOrEmail(markdownUserModel.getUsername(), markdownUserModel.getEmail())
                        .size() > 0;
                if (!found) {
                    tokenService.generateToken(markdownUserModel);
                    markdownUserModel.setPassword(passwordEncoder.encode(markdownUserModel.getPassword()));
                    userRepository.saveAndFlush(markdownUserModel);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addRoles() {
        try {
            List<MarkdownRoleModel> markdownRoleModels = new ObjectMapper()
                    .readValue(
                            new ClassPathResource("roles.json").getFile(),
                            new TypeReference<ArrayList<MarkdownRoleModel>>() {
                            }
                    );
            markdownRoleModels.forEach(markdownRoleModel -> {
                if (!roleRepository.findByRole(markdownRoleModel.getRole()).isPresent()) {
                    roleRepository.saveAndFlush(markdownRoleModel);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
