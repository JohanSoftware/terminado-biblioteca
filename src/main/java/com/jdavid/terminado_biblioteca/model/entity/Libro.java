package com.jdavid.terminado_biblioteca.model.entity;

import java.time.LocalDate;

import com.jdavid.terminado_biblioteca.model.LibroStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;



//1
@Schema(description = "Respuesta de la informacion completa de los libros")
@Data
@Entity
@Table(name = "libros")
public class Libro {  

    @Schema(description = "Identificador unico del libro", example = " 1 ")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Titulo del libro", example = " Habitos Atomicos ")
    @NotBlank(message = "El campo 'titulo' es obligatorio")
    @Size(min = 2, message = "El campo debe tener como minimo '2' caracteres")
    @Column(name = "titulo", nullable = false)
    private String titulo; 

    @Schema(description = "Autor del libro", example = " James Clear ")
    @NotBlank(message = "El campo 'autor' es obligatorio")
    @Column(name = "autor", nullable = false)
    private String autor; 

    @Schema(description = "ISBN del libro", example = " 123456 ")
    @NotBlank(message = "El campo 'isbn' es obligatorio")
    @Size(min = 1, max = 6,message = "El campo debe tener como minimo '1' y maximo '6' caracteres")
    @Column(name = "isbn", nullable = false, unique = true, length = 6)
    private String isbn; 

    @Schema(description = " Año de publicacion del libro", example = " 2025-01-01 ")
    @NotNull(message = "El campo 'añoDePublicacion' es obligatorio")
    @Past
    @Column(name = "año_de_publicacion")
    private LocalDate añoDePublicacion;

    @Schema(description = "Genero del libro", example = " Drama ")
    @NotBlank(message = "El campo 'genero' es obligatorio")
    @Column(name = "genero")
    private String genero; 

    @Schema(description = " Estado del libro", example = " Automaticamente inicia en DISPONIBLE ")
    @Column(name = "estado", nullable = false)
    private LibroStatus estado = LibroStatus.DISPONIBLE;


}
