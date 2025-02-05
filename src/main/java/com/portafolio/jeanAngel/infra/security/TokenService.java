package com.portafolio.jeanAngel.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    // Metodo para generar un token basado en UserDetails
    public String generarToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Extraer el nombre de usuario (subject) desde UserDetails
            String username = userDetails.getUsername();

            // Incluir roles como una claim (si es necesario)
            return JWT.create()
                    .withIssuer("portafolio")
                    .withSubject(username)
                    // Cambiar toList() por Collectors.toList()
                    .withClaim("roles", userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())) // Cambiado aquí
                    .withExpiresAt(generarExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token", exception);
        }
    }

    // Metodo para verificar si el token es válido
    public boolean isValidToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("portafolio")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            // Verificar si el token ha expirado
            return !jwt.getExpiresAt().before(new Date());
        } catch (JWTVerificationException exception) {
            return false;  // El token es inválido
        }
    }

    // Metodo para obtener el subject (usuario) del token
    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("portafolio")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("El token es inválido!", exception);
        }
    }

    // Metodo para generar la fecha de expiración del token (2 horas)
    private Date generarExpiracion() {
        return Date.from(LocalDateTime.now().plusHours(2)
                .atZone(ZoneId.systemDefault()).toInstant());
    }
}
