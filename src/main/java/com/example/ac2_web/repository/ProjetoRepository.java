package com.example.ac2_web.repository;

import com.example.ac2_web.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    Projeto findProjetoWithFuncionarios(@Param("id") Integer id);

    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
}
