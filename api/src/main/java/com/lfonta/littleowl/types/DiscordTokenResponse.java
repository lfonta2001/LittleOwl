package com.lfonta.littleowl.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DiscordTokenResponse(
        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("token_type")
        String tokenType,

        @JsonProperty("expires_in")
        int expiresIn,

        @JsonProperty("refresh_token")
        String refreshToken,

        String scope
) {
}
