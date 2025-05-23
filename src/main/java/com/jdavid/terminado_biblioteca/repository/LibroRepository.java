package com.jdavid.terminado_biblioteca.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdavid.terminado_biblioteca.model.Libro;


//2
public interface LibroRepository extends JpaRepository<Libro, Long>{  //Aplicamos SOor entidad si no por abtrLID (no es paccion)
    List<Libro> findByTituloIgnoringCaseContains(String titulo);
}
