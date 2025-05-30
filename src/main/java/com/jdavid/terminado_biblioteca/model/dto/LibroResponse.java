package com.jdavid.terminado_biblioteca.model.dto;

import java.time.LocalDate;

import com.jdavid.terminado_biblioteca.model.LibroStatus;

import lombok.Data;

@Data
public class LibroResponse {
    private Long LibroId;
    private String titulo;
    private String autor;
    private String isbn;
    private LocalDate añoDePublicacion;
    private String genero; 
    private LibroStatus estado;
    
}
