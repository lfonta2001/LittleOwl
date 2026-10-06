package com.lfonta.littleowl.controllers;

import com.lfonta.littleowl.dto.request.CredentialsLoginRequest;
import com.lfonta.littleowl.dto.request.OAuthLoginRequest;
import com.lfonta.littleowl.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody OAuthLoginRequest request) {
        return authService.loginWithOAuth(request.getPlatform(), request.getCode(), request.getCodeVerifier());
    }

    @PostMapping("/login/credentials")
    public ResponseEntity<?> loginCredentials(@Valid @RequestBody CredentialsLoginRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body(null);
    }

    @PostMapping("/login/refresh")
    public ResponseEntity<?> refresh(Authentication authentication) {
        System.out.println(authentication.getCredentials());
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
}
