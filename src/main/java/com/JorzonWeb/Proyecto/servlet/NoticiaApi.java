package com.JorzonWeb.Proyecto.servlet;

import com.JorzonWeb.Proyecto.modelo.Noticia;
import com.JorzonWeb.Proyecto.modelo.Popular;
import com.JorzonWeb.Proyecto.servicio.NoticiaService;
import com.JorzonWeb.Proyecto.servicio.PopularService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Controlador REST para manejar operaciones públicas relacionadas con noticias.
 */
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/noticia")
public class NoticiaApi {

    private final NoticiaService noticiaService;
    private final PopularService popularService;

    /**
     * Obtiene la lista de noticias públicas.
     *
     * @param locale El locale utilizado para la internacionalización.
     * @return Una lista de noticias.
     */
    @GetMapping
    public List<Noticia> listNoticias(Locale locale) {
        return noticiaService.listNoticias(locale);
    }

    /**
     * Obtiene una noticia específica por su ID.
     *
     * @param locale El locale utilizado para la internacionalización.
     * @param id     El ID de la noticia a obtener.
     * @return La noticia correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public Noticia noticia(Locale locale, @PathVariable("id") Long id) {
        return noticiaService.obtenerNoticia(locale, id);
    }

    /**
     * Obtiene la lista de noticias populares.
     *
     * @return Una lista de noticias populares.
     */
    @GetMapping("/popular")
    public List<Popular> listarPopulares() {
        return this.popularService.populares();
    }
}

