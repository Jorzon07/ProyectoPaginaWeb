package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.dto.Respuesta;
import com.JorzonWeb.Proyecto.exception.ServiceException;
import com.JorzonWeb.Proyecto.modelo.Role;
import com.JorzonWeb.Proyecto.modelo.Usuario;
import com.JorzonWeb.Proyecto.repository.UsuarioRepository;
import com.JorzonWeb.Proyecto.util.MessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para manejar operaciones relacionadas con usuarios.
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final MessagesUtil messagesUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${admin.secret.key}")
    private String llaveSecreta;

    /**
     * Registra un nuevo usuario.
     *
     * @param usuario Los detalles del usuario a registrar.
     * @param locale  El locale utilizado para la internacionalización.
     * @return Una respuesta que contiene información sobre el resultado del registro.
     * @throws ServiceException Si hay un error durante el registro.
     */
    public Respuesta registrarUsuario(Usuario usuario, Locale locale) {
        // Verificar si el usuario es un administrador y tiene la llave secreta correcta
        if (usuario.getRole().equals(Role.ADMIN)) {
            if (!usuario.getLlaveSecreta().equals(llaveSecreta)) {
                throw new ServiceException(messagesUtil.getMessage("usuario.registro.noadmin", locale));
            }
        }

        // Verificar si las contraseñas coinciden
        if (!usuario.getPassword().equals(usuario.getConfirmPassword())) {
            throw new ServiceException(messagesUtil.getMessage("usuario.confirmpassword.none", locale));
        }

        // Verificar si el correo ya está registrado
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new ServiceException(this.messagesUtil.getMessage("usuario.existente.correo", locale));
        }

        // Codificar la contraseña antes de almacenarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Guardar el usuario en el repositorio
        Usuario usuarioRegistrado = usuarioRepository.save(usuario);

        // Eliminar información sensible antes de devolverla
        usuarioRegistrado.setPassword(null);
        usuarioRegistrado.setLlaveSecreta(null);

        // Crear una respuesta exitosa
        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(messagesUtil.getMessage("usuario.register.successful", locale));
        respuesta.setDatos(usuarioRegistrado);

        return respuesta;
    }

    /**
     * Obtiene una lista de usuarios (sin contraseñas) para operaciones de listado.
     *
     * @return Una respuesta que contiene la lista de usuarios.
     */
    public Respuesta listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll().stream().map(u -> {
            u.setPassword(null);
            return u;
        }).collect(Collectors.toList());

        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje("Lista de usuarios");
        respuesta.setDatos(usuarios);

        return respuesta;
    }

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo El correo electrónico del usuario a buscar.
     * @param locale El locale utilizado para la internacionalización.
     * @return El objeto Usuario correspondiente al correo electrónico proporcionado.
     * @throws ServiceException Si el usuario no existe, se lanza una excepción.
     */
    public Usuario findByCorreo(String correo, Locale locale) {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new ServiceException(this.messagesUtil.getMessage("usuario.correo.nonexisting", locale)));
    }
}

