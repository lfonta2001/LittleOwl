package com.lfonta.littleowl.dto.response;

import com.lfonta.littleowl.types.SupportedAuthPlatform;

import java.util.Set;

public record LoginResponse (
        String userId,
        String accessToken,
        long accessExpiresIn,
        String refreshToken,
        long refreshExpiresIn,
        Set<SupportedAuthPlatform> authenticatedPlatforms
) {}
