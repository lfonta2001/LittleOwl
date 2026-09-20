package com.lfonta.theowlery.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;import java.util.Arrays;

@Getter
public enum SupportedAuthPlatform {
    DISCORD("discord"),
    GOOGLE("google"),
    GITHUB("github");

    private final String platform;

    SupportedAuthPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return this.platform;
    }

    @JsonValue
    public String getPlatform() {
        return platform;
    }

    @JsonCreator
    public static SupportedAuthPlatform fromPlatform(String platform) {
        return Arrays.stream(SupportedAuthPlatform.values())
                .filter(env -> env.platform.equalsIgnoreCase(platform))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown Auth Platform: " + platform));
    }
}
