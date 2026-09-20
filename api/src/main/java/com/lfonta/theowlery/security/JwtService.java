package com.lfonta.theowlery.security;

import com.lfonta.theowlery.types.User;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${security.jwt.tol}")
    private long jwtTol;
    @Value("${security.jwt.refresh.tol}")
    private long jwtRefreshTol;

    private final JwtEncoder encoder;

    private final Map<String, String> refreshTokens = new HashMap<>();

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
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
        return generateToken(user, jwtTol);
    }

    public String generateRefreshToken(User user) {
        String token = generateToken(user, jwtRefreshTol);
        refreshTokens.put(user.id(), token);
        return token;
    }

    public long getAccessTokenTOL() { return this.jwtTol; }

    public long getRefreshTokenTOL() { return this.jwtRefreshTol; }
}
