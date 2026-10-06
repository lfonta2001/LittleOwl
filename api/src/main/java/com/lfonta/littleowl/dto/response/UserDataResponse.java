package com.lfonta.littleowl.dto.response;

public record UserDataResponse(
        String userId,
        String username,
        String globalName,
        String avatarId,
        String role
) {
}
