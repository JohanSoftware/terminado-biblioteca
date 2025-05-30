package com.jdavid.terminado_biblioteca.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdavid.terminado_biblioteca.exception.BookNotFoundException;
import com.jdavid.terminado_biblioteca.model.LibroStatus;
import com.jdavid.terminado_biblioteca.model.dto.LibroRequest;
import com.jdavid.terminado_biblioteca.model.dto.LibroResponse;
import com.jdavid.terminado_biblioteca.model.entity.Libro;
import com.jdavid.terminado_biblioteca.repository.LibroRepository;
import com.jdavid.terminado_biblioteca.service.LibroService;

import lombok.RequiredArgsConstructor;

//5
@RequiredArgsConstructor // crear un constructor del atributo final
@Service // se etiqueta la implementacion, no la interface
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Override
    public LibroResponse crear(LibroRequest libro) {
        var entity = entidad(libro);
        var nuevoLibro = libroRepository.save(entity);

        return repuesta(nuevoLibro);
    }

    @Override
    public List<LibroResponse> obtenerTodos() {
        return libroRepository.findAll().stream()
                .map(this::repuesta)
                .toList();
    }

    @Override
    public LibroResponse obetenerPorId(Long id) {
        return libroRepository.findById(id)
                .map(this::repuesta)
                .orElseThrow(() -> new BookNotFoundException("No se encontro el libro con el id: " + id));
    }

    @Override
    public LibroResponse actualizarPorId(Long id, LibroRequest libro) {
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
    public List<LibroResponse> obtenerPorTitulo(String titulo) {
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

    private LibroResponse repuesta(Libro libro) {
        var libroCompl = new LibroResponse();
        libroCompl.setLibroId(libro.getId());
        libroCompl.setTitulo(libro.getTitulo());
        libroCompl.setAutor(libro.getAutor());
        libroCompl.setIsbn(libro.getIsbn());
        libroCompl.setAñoDePublicacion(libro.getAñoDePublicacion());
        libroCompl.setGenero(libro.getGenero());
        libroCompl.setEstado(libro.getEstado());

        return libroCompl;
    }

    private Libro entidad(LibroRequest libro) {
        var entity = new Libro();
        entity.setTitulo(libro.getTitulo());
        entity.setAutor(libro.getAutor());
        entity.setIsbn(libro.getIsbn());
        entity.setAñoDePublicacion(libro.getAñoDePublicacion());
        entity.setGenero(libro.getGenero());

        return entity;
    }

}
