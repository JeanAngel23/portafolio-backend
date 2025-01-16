package com.portafolio.jeanAngel.controller;

import com.portafolio.jeanAngel.dto.DataAuthenticationUser;
import com.portafolio.jeanAngel.infra.security.DatosJWT;
import com.portafolio.jeanAngel.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser) {
        try {
            // Crear el token de autenticación
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    dataAuthenticationUser.username(), // Usar únicamente el username
                    dataAuthenticationUser.password()
            );

            var usuarioAuth = authenticationManager.authenticate(authToken);

            // Obtener los detalles del usuario autenticado
            UserDetails userDetails = (UserDetails) usuarioAuth.getPrincipal();

            // Generar el token JWT
            var JWTtoken = tokenService.generarToken(userDetails);

            // Devolver el token en la respuesta
            return ResponseEntity.ok(new DatosJWT(JWTtoken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}

