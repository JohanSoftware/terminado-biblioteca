package com.jdavid.terminado_biblioteca.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdavid.terminado_biblioteca.exception.BookNotFoundException;
import com.jdavid.terminado_biblioteca.model.Libro;
import com.jdavid.terminado_biblioteca.model.LibroStatus;
import com.jdavid.terminado_biblioteca.repository.LibroRepository;
import com.jdavid.terminado_biblioteca.service.LibroService;

import lombok.RequiredArgsConstructor;

//5
@RequiredArgsConstructor // crear un constructor del atributo final
@Service // se etiqueta la implementacion, no la interface
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Override
    public Libro crear(Libro libro) {
        var entity = repuesta(libro);
        var nuevoLibro = libroRepository.save(entity);

        return repuesta(nuevoLibro);
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll().stream()
                .map(this::repuesta)
                .toList();
    }

    @Override
    public Libro obetenerPorId(Long id) {
        return libroRepository.findById(id)
                .map(this::repuesta)
                .orElseThrow(() -> new BookNotFoundException("No se encontro el libro con el id: " + id));
    }

    @Override
    public Libro actualizarPorId(Long id, Libro libro) {
        var entityOpt = libroRepository.findById(id);
        if (!entityOpt.isPresent()) {
            throw new BookNotFoundException("No existe el libro");
        }
        var entity = entidad(libro);
        entity.setId(entityOpt.get().getId());

        var entityAct = libroRepository.save(entity);

        return repuesta(entityAct);
    }

    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public List<Libro> obtenerPorTitulo(String titulo) {
        return libroRepository.findByTituloIgnoringCaseContains(titulo).stream()
                .map(this::repuesta)
                .toList();
    }

    @Override
    public void prestarLibro(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));

        if (libro.getEstado() == LibroStatus.PRESTADO) {
            throw new BookNotFoundException("El libro ya está prestado");
        }

        libro.setEstado(LibroStatus.PRESTADO);
        libroRepository.save(libro);
    }

    @Override
    public void devolverLibro(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));

        if (libro.getEstado() == LibroStatus.DISPONIBLE) {
            throw new BookNotFoundException("El libro ya está disponible");
        }

        libro.setEstado(LibroStatus.DISPONIBLE);
        libroRepository.save(libro);
    }

    private Libro repuesta(Libro libro) {
        var libroCompl = new Libro();
        libroCompl.setId(libro.getId());
        libroCompl.setTitulo(libro.getTitulo());
        libroCompl.setAutor(libro.getAutor());
        libroCompl.setIsbn(libro.getIsbn());
        libroCompl.setAñoDePublicacion(libro.getAñoDePublicacion());
        libroCompl.setGenero(libro.getGenero());
        libroCompl.setEstado(libro.getEstado());

        return libroCompl;
    }

    private Libro entidad(Libro libro) {
        var entity = new Libro();
        entity.setTitulo(libro.getTitulo());
        entity.setAutor(libro.getAutor());
        entity.setIsbn(libro.getIsbn());
        entity.setAñoDePublicacion(libro.getAñoDePublicacion());
        entity.setGenero(libro.getGenero());
        entity.setEstado(libro.getEstado());

        return entity;
    }

}
