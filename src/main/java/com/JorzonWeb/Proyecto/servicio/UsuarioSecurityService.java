package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.exception.ServiceException;
import com.JorzonWeb.Proyecto.modelo.Usuario;
import com.JorzonWeb.Proyecto.repository.UsuarioRepository;
import com.JorzonWeb.Proyecto.util.MessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class UsuarioSecurityService {

    private final UsuarioRepository usuarioRepository;
    private final MessagesUtil messagesUtil;

    public Usuario findByCorreoForSecurity(String correo) {
        return this.usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new ServiceException(this.messagesUtil.getMessage("usuario.correo.nonexisting", Locale.getDefault())
                        .replace("#correo", correo)));
    }
}
