package com.drmarkdown.auth.services.impl;

import com.drmarkdown.auth.exceptions.InvalidTokenException;
import com.drmarkdown.auth.models.MarkdownUserModel;
import com.drmarkdown.auth.services.AuthSigninKeyResolver;
import com.drmarkdown.auth.services.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private AuthSigninKeyResolver authSigninKeyResolver;

    @Override
    public void validateToken(String jwtToken) throws InvalidTokenException {
        try {
            Jwts.parserBuilder()
                    .setSigningKeyResolver(authSigninKeyResolver)
                    .build().parse(jwtToken);
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new InvalidTokenException("Invalid token", e);
        }
    }

    @Override
    public void generateToken(MarkdownUserModel markdownUserModel) {
        //TODO: Replace token with JWT
        String jwtToken = Jwts.builder()
                .setSubject(markdownUserModel.getUsername())
                .setAudience(markdownUserModel.getRoles().toString())
                .setIssuer(markdownUserModel.getId())
                .signWith(authSigninKeyResolver.getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
        markdownUserModel.setJwtToken(jwtToken);
    }
}
