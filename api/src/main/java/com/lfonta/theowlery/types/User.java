package com.lfonta.theowlery.types;

public record User (
    String id,
    String username,
    String globalName,
    String avatar,
    String role
) {}
