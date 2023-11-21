package com.JorzonWeb.Proyecto.servlet;

import com.JorzonWeb.Proyecto.dto.Respuesta;
import com.JorzonWeb.Proyecto.servicio.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para operaciones administrativas relacionadas con usuarios.
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApi {

    private final UsuarioService usuarioService;

    /**
     * Endpoint para obtener la lista de usuarios.
     *
     * @return Una respuesta que contiene la lista de usuarios o información sobre el error.
     */
    @GetMapping("/usuarios")
    public Respuesta listarUsuarios() {
        // Llamar al servicio para obtener la lista de usuarios
        Respuesta respuesta = usuarioService.listarUsuarios();

        // Devolver la respuesta obtenida del servicio
        return respuesta;
    }
}

