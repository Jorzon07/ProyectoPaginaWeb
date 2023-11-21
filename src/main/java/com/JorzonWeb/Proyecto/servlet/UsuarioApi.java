package com.JorzonWeb.Proyecto.servlet;


import com.JorzonWeb.Proyecto.dto.Respuesta;
import com.JorzonWeb.Proyecto.modelo.Role;
import com.JorzonWeb.Proyecto.modelo.Usuario;
import com.JorzonWeb.Proyecto.servicio.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Secured("ADMIN")
    @GetMapping()
    public Respuesta listUsuarios(){
        return usuarioService.listarUsuarios();
    }
}
