package com.jdavid.terminado_biblioteca.service;

import java.util.List;

import com.jdavid.terminado_biblioteca.model.dto.LibroRequest;
import com.jdavid.terminado_biblioteca.model.dto.LibroResponse;

;
//3
public interface LibroService { 

    LibroResponse crear(LibroRequest libro); //Crear un nuevo libro en el sistema.

    List<LibroResponse> obtenerTodos();  //Obtener un listado de todos los libros.

    LibroResponse obetenerPorId(Long id);  //Obtener los detalles de un libro específico por su ID.

    LibroResponse actualizarPorId(Long id, LibroRequest libro); //Actualizar la información de un libro por su ID.

    void eliminar(Long id); //Eliminar un libro por su ID.

    List<LibroResponse> obtenerPorTitulo(String titulo); //Buscar libros por titulo

    void prestarLibro(Long id); // Marcar un libro como PRESTADO

    void devolverLibro(Long id);// Marcar un libro como DISPONIBLE

}
