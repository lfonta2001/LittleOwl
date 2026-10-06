package com.lfonta.littleowl.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.lfonta.littleowl.types.SupportedAuthPlatform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OAuthLoginRequest {
    @NotNull
    private SupportedAuthPlatform platform;
    @NotBlank
    private String code;
    @NotBlank
    @JsonAlias("code_verifier")
    private String codeVerifier;
}
