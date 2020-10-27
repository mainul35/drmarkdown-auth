package com.drmarkdown.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping("/validate")
    public void validateToken(HttpServletRequest request) throws Exception {
        throw new Exception("Some exception");
    }
}
