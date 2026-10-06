package com.lfonta.littleowl.security;

import com.lfonta.littleowl.types.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final long jwtTtl;
    private final long jwtRefreshTtl;

    private final JwtEncoder encoder;
    private final Map<String, String> refreshTokens = new HashMap<>();

    public JwtService(JwtEncoder encoder, @Qualifier("jwtTtl") long jwtTtl, @Qualifier("jwtRefreshTtl") long jwtRefreshTtl) {
        this.encoder = encoder;
        this.jwtTtl = jwtTtl;
        this.jwtRefreshTtl = jwtRefreshTtl;
    }

    public String generateToken(User user, long expirationTime) {
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .subject(user.id())
                .claim("role", user.role())
                .issuedAt(now)
                .expiresAt(now.plus(expirationTime, ChronoUnit.MILLIS))
                .build();

        return encoder.encode(
                JwtEncoderParameters.from(claimsSet)
        ).getTokenValue();
    }

    public String generateToken(User user) {
        return generateToken(user, jwtTtl);
    }

    public String generateRefreshToken(User user) {
        String token = generateToken(user, jwtRefreshTtl);
        refreshTokens.put(user.id(), token);
        return token;
    }

    public long getAccessTokenTTL() { return this.jwtTtl; }

    public long getRefreshTokenTTL() { return this.jwtRefreshTtl; }
}
