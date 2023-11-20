package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.config.security.JwtService;
import com.JorzonWeb.Proyecto.dto.LoginRequest;
import com.JorzonWeb.Proyecto.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtService jwtService;

    private final UsuarioService usuarioService;

    private final AuthenticationManager authenticationManager;

    public Usuario login(LoginRequest request, Locale locale) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword())
        );

        Usuario usuario = usuarioService.findByCorreo(request.getCorreo(), locale);

        String token = jwtService.generateToken(usuario);

        return Optional.of(usuario).map(u -> {
            u.setToken(token);
            u.setPassword(null);
            return u;
        }).orElse(null);
    }
}
