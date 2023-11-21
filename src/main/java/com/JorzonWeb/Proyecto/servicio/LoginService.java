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

/**
 * Servicio para manejar la autenticación de usuarios.
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    /**
     * Realiza la autenticación de un usuario y genera un token JWT.
     *
     * @param request Los detalles de inicio de sesión proporcionados.
     * @param locale  El locale utilizado para la búsqueda del usuario.
     * @return El objeto Usuario con el token generado y sin la contraseña.
     */
    public Usuario login(LoginRequest request, Locale locale) {
        // Autenticar al usuario con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword())
        );

        // Obtener el usuario autenticado
        Usuario usuario = usuarioService.findByCorreo(request.getCorreo(), locale);

        // Generar el token JWT para el usuario
        String token = jwtService.generateToken(usuario);

        // Asignar el token al usuario y eliminar la contraseña antes de devolverlo
        return Optional.of(usuario).map(u -> {
            u.setToken(token);
            u.setPassword(null);
            return u;
        }).orElse(null);
    }
}
