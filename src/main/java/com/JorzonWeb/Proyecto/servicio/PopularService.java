package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.modelo.Popular;
import com.JorzonWeb.Proyecto.repository.PopularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PopularService {

    private final PopularRepository popularRepository;

    public List<Popular> populares(){
        return this.popularRepository.findAll();
    }

}
