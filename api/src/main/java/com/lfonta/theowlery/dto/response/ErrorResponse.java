package com.lfonta.theowlery.dto.response;

public record ErrorResponse(
        String error,
        String message
) {
}
