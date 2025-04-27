package com.example.ac2_web.controllers;

import com.example.ac2_web.dto.DadosProjetoDTO;
import com.example.ac2_web.dto.FuncionarioDTO;
import com.example.ac2_web.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public void adicionar(@RequestBody FuncionarioDTO dto) {
        funcionarioService.adicionar(dto);
    }

    @GetMapping("/{id}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable Integer id) {
        return funcionarioService.buscarProjetos(id);
    }
}
