package com.example.ac2_web.dto;

import java.util.List;

public class DadosSetorDTO {

    private Integer id;
    private String nome;
    private List<String> funcionarios;

    public DadosSetorDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<String> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
