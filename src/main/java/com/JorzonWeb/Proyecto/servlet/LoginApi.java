package com.JorzonWeb.Proyecto.servlet;

import com.JorzonWeb.Proyecto.dto.LoginRequest;
import com.JorzonWeb.Proyecto.dto.Respuesta;
import com.JorzonWeb.Proyecto.servicio.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * Controlador REST para manejar operaciones de inicio de sesión.
 */
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginApi {

    private final LoginService authenticationService;

    /**
     * Endpoint para realizar la operación de inicio de sesión.
     *
     * @param loginRequest Los detalles de inicio de sesión proporcionados.
     * @param locale       El locale utilizado para la internacionalización.
     * @return Una respuesta que contiene un mensaje y los datos del usuario autenticado.
     */
    @PostMapping
    public Respuesta login(@Valid @RequestBody LoginRequest loginRequest, Locale locale) {
        // Crear una respuesta con un mensaje predeterminado
        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje("Login exitoso");

        // Obtener los datos del usuario autenticado a través del servicio de inicio de sesión
        respuesta.setDatos(authenticationService.login(loginRequest, locale));

        // Devolver la respuesta
        return respuesta;
    }
}

