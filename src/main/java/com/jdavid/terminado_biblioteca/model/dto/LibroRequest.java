package com.jdavid.terminado_biblioteca.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LibroRequest {
    private String titulo;
    private String autor;
    private String isbn;
    private LocalDate añoDePublicacion;
    private String genero;
}
