package com.JorzonWeb.Proyecto.modelo;

import com.JorzonWeb.Proyecto.util.StringListConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="noticia")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 255)
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String imagenSlide;
    private String imagenCaratula;
    private String imagenNoticia;
    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> imagenes;
    private Date fecha;

}
