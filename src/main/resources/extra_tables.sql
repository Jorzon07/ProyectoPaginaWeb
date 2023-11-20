CREATE TABLE public.noticia_ml (
    id INT,
    titulo VARCHAR(255),
    descripcion TEXT,
    idioma VARCHAR(10),
    PRIMARY KEY (id, idioma),
    FOREIGN KEY (id) REFERENCES public.noticia(id)
);