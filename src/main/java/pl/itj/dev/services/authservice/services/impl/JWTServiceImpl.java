package pl.itj.dev.services.authservice.services.impl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.itj.dev.services.authservice.services.ifc.JWTService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTServiceImpl implements JWTService {

    @Value("${jwt.secretKey}")
    String secretKey;

    @Override
    public Optional<String> generateToken(UserDetails user) {
        Date issueDate = Date.from(Instant.now());
        Date expireDate = Date.from(issueDate.toInstant().plus(30, ChronoUnit.MINUTES));

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .claim("roles", user.getAuthorities())
                .signWith(SignatureAlgorithm.HS256, secretKey);

        return Optional.of(jwtBuilder.compact());
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
