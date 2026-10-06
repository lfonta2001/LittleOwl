package com.lfonta.littleowl.types;

import java.util.Set;

public record UserAuthData(
        boolean loggedIn,
        Set<SupportedAuthPlatform> authenticatedPlatforms
) {
}
