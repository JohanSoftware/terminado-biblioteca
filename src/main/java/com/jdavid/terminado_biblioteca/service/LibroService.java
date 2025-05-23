package com.jdavid.terminado_biblioteca.service;

import java.util.List;

import com.jdavid.terminado_biblioteca.model.Libro;

;
//3
public interface LibroService { 

    Libro crear(Libro libro); //Crear un nuevo libro en el sistema.

    List<Libro> obtenerTodos();  //Obtener un listado de todos los libros.

    Libro obetenerPorId(Long id);  //Obtener los detalles de un libro específico por su ID.

    Libro actualizarPorId(Long id, Libro libro); //Actualizar la información de un libro por su ID.

    void eliminar(Long id); //Eliminar un libro por su ID.

    List<Libro> obtenerPorTitulo(String titulo); //Buscar libros por titulo

    void prestarLibro(Long id); // Marcar un libro como PRESTADO

    void devolverLibro(Long id);// Marcar un libro como DISPONIBLE

}
