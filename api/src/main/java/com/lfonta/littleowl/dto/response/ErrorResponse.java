package com.lfonta.littleowl.dto.response;

public record ErrorResponse(
        String error,
        String message
) {
}
