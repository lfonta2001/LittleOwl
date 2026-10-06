package com.lfonta.littleowl.persistence.config;

public record DatabaseProperties(
        String host,
        int port,
        String database,
        String username,
        String password
) {}
