package com.JorzonWeb.Proyecto.servlet;

import com.JorzonWeb.Proyecto.modelo.Noticia;
import com.JorzonWeb.Proyecto.modelo.Popular;
import com.JorzonWeb.Proyecto.servicio.NoticiaService;
import com.JorzonWeb.Proyecto.servicio.PopularService;
import com.JorzonWeb.Proyecto.util.MessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/noticia")
public class NoticiaApi {

    private final NoticiaService noticiaService;

    private final PopularService popularService;

    @GetMapping
    public List<Noticia> listNoticias(Locale locale){
        return noticiaService.listNoticias(locale);
    }

    @GetMapping("/{id}")
    public Noticia noticia(Locale locale, @PathVariable("id") Long id){
        return noticiaService.obtenerNoticia(locale, id);
    }

    @GetMapping("/popular")
    public List<Popular> listarPopulares(){
        return this.popularService.populares();
    }
}
