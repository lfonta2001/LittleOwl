package com.lfonta.littleowl.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CredentialsLoginRequest {
    @NotBlank
    private String identifier;
    @NotBlank
    private String password;
}
