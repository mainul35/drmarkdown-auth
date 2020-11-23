package com.drmarkdown.auth.services.impl;

import com.drmarkdown.auth.dtos.UserInfoDto;
import com.drmarkdown.auth.dtos.UserLoginDto;
import com.drmarkdown.auth.models.MarkdownRoleModel;
import com.drmarkdown.auth.models.MarkdownUserModel;
import com.drmarkdown.auth.repositories.RoleRepository;
import com.drmarkdown.auth.repositories.UserRepository;
import com.drmarkdown.auth.services.TokenService;
import com.drmarkdown.auth.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserInfoDto userInfoDto) {

        if (userRepository.findByUsername(userInfoDto.getUsername()).isPresent()) {
            throw new DuplicateKeyException("Username is already registered");
        }

        if (userRepository.findByEmail(userInfoDto.getEmail()).isPresent()) {
            throw new DuplicateKeyException("Email address is already registered");
        }
        // Transform userInfoDto to markdown role model
        MarkdownUserModel markdownUserModel = new MarkdownUserModel(userInfoDto);

        // Hash the password first
        checkNotNull(userInfoDto.getPassword());

        if (Optional.ofNullable(userInfoDto.getRoles()).isEmpty()) {
            userInfoDto.setRoles(Collections.singletonList("USER"));
        }

        markdownUserModel.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));

        // Assign default role for user
        markdownUserModel.setRoles(
                roleRepository.findAll().stream()
                        .map(MarkdownRoleModel::getRole)
                        .filter(role -> matchRole(role, userInfoDto.getRoles()))
                        .collect(Collectors.toList())
        );

        // Generate a new token for the user
        tokenService.generateToken(markdownUserModel);

        // Save markdown role model
        userRepository.save(markdownUserModel);

        // update the userInfoDto after the markdown role Model has been saved
        userInfoDto.mapEntityToDto(markdownUserModel);
    }

    private boolean matchRole(String role, List<String> roles) {
        return roles.contains(role);
    }

    @Override
    public UserInfoDto retrieveUserInfo(String userId) {
        Optional<MarkdownUserModel> markdownUserModel = userRepository.findById(userId);
        if (markdownUserModel.isPresent()) {
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.mapEntityToDto(markdownUserModel.get());
            return userInfoDto;
        }
        return null;
    }

    @Override
    public UserInfoDto loginUser(UserLoginDto userLoginDto) {
        Optional<MarkdownUserModel> optionalMarkdownUserModel = userRepository.findByUsername(userLoginDto.getUsername());
        if (optionalMarkdownUserModel.isPresent()) {
            MarkdownUserModel markdownUserModel = optionalMarkdownUserModel.get();
            if (passwordEncoder.matches(userLoginDto.getPassword(), markdownUserModel.getPassword())) {
                return modelMapper.map(markdownUserModel, UserInfoDto.class);
            } else {
                throw new BadCredentialsException("Password mismatched");
            }
        } else {
            throw new BadCredentialsException("Invalid username");
        }
    }
}
