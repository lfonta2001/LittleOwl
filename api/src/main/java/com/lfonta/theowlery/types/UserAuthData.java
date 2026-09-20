package com.lfonta.theowlery.types;

import java.util.Set;

public record UserAuthData(
        boolean loggedIn,
        Set<SupportedAuthPlatform> authenticatedPlatforms
) {
}
