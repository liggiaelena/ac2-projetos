package com.example.ac2_web.controllers;

import com.example.ac2_web.dto.DadosSetorDTO;
import com.example.ac2_web.dto.SetorDTO;
import com.example.ac2_web.service.SetorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @PostMapping
    public void adicionar(@RequestBody SetorDTO dto) {
        setorService.adicionar(dto);
    }

    @GetMapping("/{id}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer id) {
        return setorService.buscarPorId(id);
    }

    @GetMapping
    public List<DadosSetorDTO> listarTodos() {
        return setorService.listarTodos();
    }

}

