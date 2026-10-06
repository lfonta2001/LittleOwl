package com.lfonta.littleowl.services;

import com.lfonta.littleowl.dto.response.LoginResponse;
import com.lfonta.littleowl.dto.response.RefreshResponse;
import com.lfonta.littleowl.factories.OAuthClientFactory;
import com.lfonta.littleowl.security.JwtService;
import com.lfonta.littleowl.types.MappableAuthUserData;
import com.lfonta.littleowl.types.SupportedAuthPlatform;
import com.lfonta.littleowl.types.User;
import com.lfonta.littleowl.types.UserAuthData;
import com.lfonta.littleowl.types.interfaces.OAuthClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final JwtService jwtService;
    private final UserService userService;
    private final OAuthClientFactory oauthClientFactory;

    public AuthService(JwtService jwtService, UserService userService, OAuthClientFactory oauthClientFactory) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.oauthClientFactory = oauthClientFactory;
    }

    public ResponseEntity<LoginResponse> loginWithOAuth(SupportedAuthPlatform platform, String code, String codeVerifier) {
        OAuthClient client = oauthClientFactory.get(platform);

        MappableAuthUserData data = client.exchangeCode(code, codeVerifier);

        logger.info(String.valueOf(data));

        User userData = userService.matchUser(platform, data);
        UserAuthData authData = userService.getAuthData(userData.id());

        String jwt = jwtService.generateToken(userData);
        String refreshToken = jwtService.generateRefreshToken(userData);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new LoginResponse(userData.id(), jwt, jwtService.getAccessTokenTTL(), refreshToken, jwtService.getRefreshTokenTTL(), authData.authenticatedPlatforms()));
    }

    public LoginResponse loginWithCredentials() {
        return null;
    }

    public ResponseEntity<RefreshResponse> refreshToken(String token) {
        return null;
    }
}
