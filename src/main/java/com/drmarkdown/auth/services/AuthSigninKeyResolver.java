package com.drmarkdown.auth.services;

import io.jsonwebtoken.SigningKeyResolver;

import javax.crypto.SecretKey;

public interface AuthSigninKeyResolver extends SigningKeyResolver {

    SecretKey getSecretKey();
}
