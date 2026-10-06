package com.lfonta.littleowl.clients;

import com.lfonta.littleowl.types.GithubAuthUserData;
import com.lfonta.littleowl.types.interfaces.OAuthClient;
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
