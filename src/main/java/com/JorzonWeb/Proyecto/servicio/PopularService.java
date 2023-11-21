package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.modelo.Popular;
import com.JorzonWeb.Proyecto.repository.PopularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para manejar operaciones relacionadas con elementos populares.
 */
@RequiredArgsConstructor
@Service
public class PopularService {

    private final PopularRepository popularRepository;

    /**
     * Obtiene la lista de elementos populares.
     *
     * @return Una lista de elementos populares.
     */
    public List<Popular> populares() {
        return this.popularRepository.findAll();
    }
}
