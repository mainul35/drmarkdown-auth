package com.drmarkdown.auth.services;

import com.drmarkdown.auth.exceptions.InvalidTokenException;
import com.drmarkdown.auth.models.MarkdownUserModel;

public interface TokenService {

    void validateToken(String jwtToken) throws InvalidTokenException;

    void generateToken(MarkdownUserModel markdownUserModel);
}
