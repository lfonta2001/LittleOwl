package com.lfonta.littleowl.clients;

import com.lfonta.littleowl.types.GoogleAuthUserData;
import com.lfonta.littleowl.types.interfaces.OAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuthClient implements OAuthClient {

    @Value("${oauth.google.client.id}")
    private String clientId;
    @Value("${oauth.google.client.secret}")
    private String clientSecret;
    @Value("${oauth.google.redirect}")
    private String redirectUri;

    @Override
    public GoogleAuthUserData exchangeCode(String code, String codeVerifier) {
        return null;
    }
}
