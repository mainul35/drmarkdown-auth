package com.drmarkdown.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class MarkdownAuthenticationException extends AuthenticationException {
    public MarkdownAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MarkdownAuthenticationException(String message) {
        super(message);
    }
}
