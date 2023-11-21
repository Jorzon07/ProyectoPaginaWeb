package com.JorzonWeb.Proyecto.repository;

import com.JorzonWeb.Proyecto.modelo.Popular;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Popular.
 */
public interface PopularRepository extends JpaRepository<Popular, Long> {
}
