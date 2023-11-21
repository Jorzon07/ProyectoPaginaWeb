package com.JorzonWeb.Proyecto.repository;

import com.JorzonWeb.Proyecto.modelo.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Noticia.
 */
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    /**
     * Obtiene la lista de noticias filtrada por idioma.
     *
     * @param idioma El idioma utilizado para la internacionalización.
     * @return Una lista de noticias filtrada por idioma.
     */
    @Query(value = "select " +
            " n.id, nml.titulo, nml.descripcion, n.imagen_slide, n.imagen_caratula, n.imagen_noticia, n.imagenes, n.fecha " +
            "from noticia n inner join noticia_ml nml on n.id = nml.id where nml.idioma = :idioma", nativeQuery = true)
    public List<Noticia> findAllByLocale(@Param("idioma") String idioma);

    /**
     * Obtiene una noticia específica por su ID y idioma.
     *
     * @param idioma El idioma utilizado para la internacionalización.
     * @param id     El ID de la noticia a obtener.
     * @return La noticia correspondiente al ID y idioma proporcionados.
     */
    @Query(value = "select " +
            " n.id, nml.titulo, nml.descripcion, n.imagen_slide, n.imagen_caratula, n.imagen_noticia, n.imagenes, n.fecha " +
            "from noticia n inner join noticia_ml nml on n.id = nml.id where nml.idioma = :idioma and n.id = :id", nativeQuery = true)
    public Noticia findByIdAndLocale(@Param("idioma") String idioma, @Param("id") Long id);
}
