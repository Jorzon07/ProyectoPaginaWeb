package com.JorzonWeb.Proyecto.servlet;

import com.JorzonWeb.Proyecto.dto.Respuesta;
import com.JorzonWeb.Proyecto.servicio.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApi {

    private final UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public Respuesta listarUsuarios(){
        Respuesta respuesta = usuarioService.listarUsuarios();
        return respuesta;
    }

}
