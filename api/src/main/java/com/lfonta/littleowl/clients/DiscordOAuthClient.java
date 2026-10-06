package com.lfonta.littleowl.clients;

import com.lfonta.littleowl.exceptions.InvalidCredentialsException;
import com.lfonta.littleowl.types.DiscordAuthUserData;
import com.lfonta.littleowl.types.DiscordTokenResponse;
import com.lfonta.littleowl.types.interfaces.OAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.rmi.UnexpectedException;

@Component
public class DiscordOAuthClient implements OAuthClient {

    @Value("${oauth.discord.client.id}")
    private String clientId;
    @Value("${oauth.discord.client.secret}")
    private String clientSecret;
    @Value("${oauth.discord.redirect}")
    private String redirectUri;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public DiscordOAuthClient(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public DiscordAuthUserData exchangeCode(String code, String codeVerifier) {
        try {
            String tokenForm = "grant_type=authorization_code"
                    + "&code=" + URLEncoder.encode(code, StandardCharsets.UTF_8)
                    + "&code_verifier=" + URLEncoder.encode(codeVerifier, StandardCharsets.UTF_8)
                    + "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8)
                    + "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
                    + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);

            HttpRequest tokenRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://discord.com/api/v10/oauth2/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(tokenForm))
                    .build();

            HttpResponse<String> tokenResponse = httpClient.send(tokenRequest, HttpResponse.BodyHandlers.ofString());

            if (tokenResponse.statusCode() != 200) {
                throw new InvalidCredentialsException();
            }

            DiscordTokenResponse tokenData = objectMapper.readValue(
                    tokenResponse.body(),
                    DiscordTokenResponse.class
            );

            HttpRequest userRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://discord.com/api/v10/users/@me"))
                    .header("Authorization", String.join(" ", tokenData.tokenType(), tokenData.accessToken()))
                    .GET()
                    .build();

            HttpResponse<String> userResponse = httpClient.send(userRequest, HttpResponse.BodyHandlers.ofString());

            if (userResponse.statusCode() != 200) {
                throw new UnexpectedException("I don't actually know why this happened");
            }

            return objectMapper.readValue(userResponse.body(), DiscordAuthUserData.class);
        } catch (Exception e) {
            throw new InvalidCredentialsException(e);
        }
    }
}
