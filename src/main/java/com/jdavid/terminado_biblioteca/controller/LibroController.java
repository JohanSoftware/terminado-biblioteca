package com.jdavid.terminado_biblioteca.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdavid.terminado_biblioteca.model.dto.LibroRequest;
import com.jdavid.terminado_biblioteca.model.dto.LibroResponse;
import com.jdavid.terminado_biblioteca.service.LibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

//4
@Tag(name = "Libros", description = "Endpoint para gestionar los libros")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    @Operation(summary = "Crear un nuevo libro", description = "Crea un nuevo libro en el sistema con los datos proporcionados.")
    @PostMapping
    public LibroResponse createBook(@Valid @RequestBody LibroRequest libro) {
        return libroService.crear(libro);
    }

    @Operation(summary = "Listar todos los libros", description = "Lista de todos los libros existentes. Incluyendo libros disponibles y prestados.")
    @GetMapping
    public List<LibroResponse> getAll() {
        return libroService.obtenerTodos();
    }

    @Operation(summary = "Obtener libro por ID", description = "Obtiene un libro específico utilizando su identificador único.")
    @GetMapping("{id}")
    public LibroResponse  getById(@PathVariable Long id) {
        return libroService.obetenerPorId(id);
    }

    @Operation(summary = "Actualizar libro", description = "Actualiza la información de un libro existente utilizando su ID.")
    @PutMapping("{id}")
    public LibroResponse updateBook(@Valid @Min(1) @PathVariable Long id, @Valid @RequestBody LibroRequest libro) {
        return libroService.actualizarPorId(id, libro);
    }

    @Operation(summary = "Eliminar libro", description = "Elimina un libro del sistema utilizando su ID.")
    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        libroService.eliminar(id);
    }

    @Operation(summary = "Buscar libros por título", description = "Busca libros que coincidan con el título proporcionado.")
    @GetMapping("buscar")
    public List<LibroResponse> getByTitle(@RequestParam(name = "titulo") String title) {
        return libroService.obtenerPorTitulo(title);
    }

    @Operation(summary = "Prestar libro", description = "Marca un libro como prestado utilizando su ID y devuelve la lista actualizada de libros.")
    @PostMapping("{id}/prestar")
    public List<LibroResponse> prestarLibro(@PathVariable Long id) {
        libroService.prestarLibro(id);
        return libroService.obtenerTodos();
    }

    @Operation(summary = "Devolver libro", description = "Marca un libro como dispible utilizando su ID y devuelve la lista actualizada de libros.")
    @PutMapping("{id}/devolver")
    public List<LibroResponse> devolverLibro(@PathVariable Long id) {
        libroService.devolverLibro(id);
        return libroService.obtenerTodos();
    }

}
