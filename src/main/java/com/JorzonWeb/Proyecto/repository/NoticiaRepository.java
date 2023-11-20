package com.JorzonWeb.Proyecto.repository;

import com.JorzonWeb.Proyecto.modelo.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    @Query(value = "select " +
            " n.id, nml.titulo, nml.descripcion, n.imagen_slide, n.imagen_caratula, n.imagen_noticia, n.imagenes, n.fecha " +
            "from noticia n inner join noticia_ml nml on n.id = nml.id where nml.idioma = :idioma", nativeQuery = true)
    public List<Noticia> findAllByLocale(@Param("idioma") String idioma);

    @Query(value = "select " +
            " n.id, nml.titulo, nml.descripcion, n.imagen_slide, n.imagen_caratula, n.imagen_noticia, n.imagenes, n.fecha " +
            "from noticia n inner join noticia_ml nml on n.id = nml.id where nml.idioma = :idioma and n.id = :id", nativeQuery = true)
    public Noticia findByIdAndLocale(@Param("idioma") String idioma, @Param("id") Long id);
}
