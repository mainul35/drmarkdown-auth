package com.drmarkdown.auth.config.security;

import com.drmarkdown.auth.exceptions.InvalidTokenException;
import com.drmarkdown.auth.exceptions.MarkdownAuthenticationException;
import com.drmarkdown.auth.models.MarkdownUserModel;
import com.drmarkdown.auth.repositories.RoleRepository;
import com.drmarkdown.auth.repositories.UserRepository;
import com.drmarkdown.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MarkdownAuthProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        final String token = (String) usernamePasswordAuthenticationToken.getCredentials();
        if (token.isEmpty()) {
            return new User(username, "", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        }
        Optional<MarkdownUserModel> optionalMarkdownUserModel = userRepository.findByUsername(username);

        if (optionalMarkdownUserModel.isPresent()) {
            MarkdownUserModel markdownUserModel = optionalMarkdownUserModel.get();
            try {
                tokenService.validateToken(markdownUserModel.getJwtToken());
            } catch (InvalidTokenException e) {
                markdownUserModel.setJwtToken(null);
                userRepository.save(markdownUserModel);
                return null;
            }

            return new User(username, "",
                    AuthorityUtils.createAuthorityList(markdownUserModel.getRoles().stream().map(s -> "ROLE_" + s).toArray(String[]::new))
            );
        }
        throw new MarkdownAuthenticationException("No such user found with this username");
    }
}
