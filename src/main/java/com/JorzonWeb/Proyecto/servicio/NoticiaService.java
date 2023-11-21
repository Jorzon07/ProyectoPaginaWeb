package com.JorzonWeb.Proyecto.servicio;

import com.JorzonWeb.Proyecto.modelo.Noticia;
import com.JorzonWeb.Proyecto.repository.NoticiaRepository;
import com.JorzonWeb.Proyecto.util.LocaleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Servicio para manejar operaciones relacionadas con noticias.
 */
@RequiredArgsConstructor
@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    /**
     * Obtiene la lista de noticias según el locale especificado.
     *
     * @param locale El locale utilizado para la internacionalización.
     * @return Una lista de noticias.
     */
    public List<Noticia> listNoticias(Locale locale) {
        if (LocaleUtil.checkDefaultLocale(locale)) {
            return noticiaRepository.findAll();
        }
        return noticiaRepository.findAllByLocale(locale.toString());
    }

    /**
     * Obtiene una noticia específica por su ID y locale.
     *
     * @param locale El locale utilizado para la internacionalización.
     * @param id     El ID de la noticia a obtener.
     * @return La noticia correspondiente al ID y locale proporcionados.
     */
    public Noticia obtenerNoticia(Locale locale, Long id) {
        if (LocaleUtil.checkDefaultLocale(locale)) {
            return noticiaRepository.findById(id).orElse(null);
        }
        return noticiaRepository.findByIdAndLocale(locale.toString(), id);
    }
}

