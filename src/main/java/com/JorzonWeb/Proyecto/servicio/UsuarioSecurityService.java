package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.exception.ServiceException;
import com.JorzonWeb.Proyecto.modelo.Usuario;
import com.JorzonWeb.Proyecto.repository.UsuarioRepository;
import com.JorzonWeb.Proyecto.util.MessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Servicio para operaciones de seguridad relacionadas con usuarios.
 */
@RequiredArgsConstructor
@Service
public class UsuarioSecurityService {

    private final UsuarioRepository usuarioRepository;
    private final MessagesUtil messagesUtil;

    /**
     * Busca un usuario por su correo electrónico para operaciones de seguridad.
     *
     * @param correo El correo electrónico del usuario a buscar.
     * @return El objeto Usuario correspondiente al correo electrónico proporcionado.
     * @throws ServiceException Si el usuario no existe, se lanza una excepción.
     */
    public Usuario findByCorreoForSecurity(String correo) {
        return this.usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new ServiceException(this.messagesUtil.getMessage("usuario.correo.nonexisting", Locale.getDefault())
                        .replace("#correo", correo)));
    }
}

