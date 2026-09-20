package com.lfonta.theowlery.clients;

import com.lfonta.theowlery.types.GoogleAuthUserData;
import com.lfonta.theowlery.types.interfaces.OAuthClient;
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
