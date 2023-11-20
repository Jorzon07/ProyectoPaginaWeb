package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.modelo.Noticia;
import com.JorzonWeb.Proyecto.repository.NoticiaRepository;
import com.JorzonWeb.Proyecto.util.LocaleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    public List<Noticia> listNoticias(Locale locale) {

        if (LocaleUtil.checkDefaultLocale(locale)) {
            return noticiaRepository.findAll();
        }
        return noticiaRepository.findAllByLocale(locale.toString());
    }

    public Noticia obtenerNoticia(Locale locale, Long id) {

        if (LocaleUtil.checkDefaultLocale(locale)) {
            return noticiaRepository.findById(id).get();
        }
        return noticiaRepository.findByIdAndLocale(locale.toString(), id);
    }
}
