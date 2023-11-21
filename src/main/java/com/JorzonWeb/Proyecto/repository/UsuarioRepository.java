package com.JorzonWeb.Proyecto.repository;

import com.JorzonWeb.Proyecto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo El correo electrónico del usuario a buscar.
     * @return Un Optional que contiene el usuario correspondiente al correo electrónico proporcionado, o vacío si no se encuentra.
     */
    public Optional<Usuario> findByCorreo(String correo);
}

