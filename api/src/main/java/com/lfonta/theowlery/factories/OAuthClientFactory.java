package com.lfonta.theowlery.factories;

import com.lfonta.theowlery.clients.DiscordOAuthClient;
import com.lfonta.theowlery.clients.GithubOAuthClient;
import com.lfonta.theowlery.clients.GoogleOAuthClient;
import com.lfonta.theowlery.types.SupportedAuthPlatform;
import com.lfonta.theowlery.types.interfaces.OAuthClient;
import org.springframework.stereotype.Component;

@Component
public class OAuthClientFactory {

    public final DiscordOAuthClient discordClient;
    public final GoogleOAuthClient googleClient;
    public final GithubOAuthClient githubClient;

    public OAuthClientFactory(DiscordOAuthClient discordClient, GoogleOAuthClient googleClient, GithubOAuthClient githubClient) {
        this.discordClient = discordClient;
        this.googleClient = googleClient;
        this.githubClient = githubClient;
    }

    public OAuthClient get(SupportedAuthPlatform platform) {
        return switch(platform) {
            case DISCORD -> discordClient;
            case GOOGLE -> googleClient;
            case GITHUB -> githubClient;
        };
    }
}
