package com.lfonta.theowlery.clients;

import com.lfonta.theowlery.types.GithubAuthUserData;
import com.lfonta.theowlery.types.interfaces.OAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GithubOAuthClient implements OAuthClient {

    @Value("${oauth.github.client.id}")
    private String clientId;
    @Value("${oauth.github.client.secret}")
    private String clientSecret;
    @Value("${oauth.github.redirect}")
    private String redirectUri;

    @Override
    public GithubAuthUserData exchangeCode(String code, String codeVerifier) {
        return null;
    }
}
