package com.JorzonWeb.Proyecto.servlet;


import com.JorzonWeb.Proyecto.dto.Respuesta;
import com.JorzonWeb.Proyecto.modelo.Usuario;
import com.JorzonWeb.Proyecto.servicio.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioApi {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Respuesta> registro(@Valid @RequestBody Usuario usuario, Locale locale) {

        Respuesta respuesta = usuarioService.registrarUsuario(usuario, locale);
        return ResponseEntity.ok(respuesta);
    }
}
