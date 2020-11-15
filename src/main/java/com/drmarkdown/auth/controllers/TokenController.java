package com.drmarkdown.auth.controllers;

import com.drmarkdown.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/validate")
    public void validateToken(HttpServletRequest request) throws Exception {
        String jwtToken = "";
        tokenService.validateToken(jwtToken);
    }
}
