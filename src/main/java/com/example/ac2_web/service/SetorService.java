package com.example.ac2_web.service;

import com.example.ac2_web.dto.DadosSetorDTO;
import com.example.ac2_web.dto.SetorDTO;
import com.example.ac2_web.entity.Funcionario;
import com.example.ac2_web.entity.Setor;
import com.example.ac2_web.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public void adicionar(SetorDTO dto) {
        Setor setor = new Setor();
        setor.setNome(dto.getNome());
        setorRepository.save(setor);
    }

    public DadosSetorDTO buscarPorId(Integer idSetor) {
        List<Setor> setores = setorRepository.findAllWithFuncionarios();

        Setor setor = setores.stream()
                .filter(s -> s.getId().equals(idSetor))
                .findFirst()
                .orElseThrow();

        DadosSetorDTO dto = new DadosSetorDTO();
        dto.setId(setor.getId());
        dto.setNome(setor.getNome());

        List<String> nomes = setor.getFuncionarios()
                .stream()
                .map(Funcionario::getNome)
                .collect(Collectors.toList());

        dto.setFuncionarios(nomes);
        return dto;
    }

    public List<DadosSetorDTO> listarTodos() {
        List<Setor> setores = setorRepository.findAll();
    
        return setores.stream().map(setor -> {
            DadosSetorDTO dto = new DadosSetorDTO();
            dto.setId(setor.getId());
            dto.setNome(setor.getNome());
    
            List<String> funcionarios = setor.getFuncionarios()
                    .stream()
                    .map(Funcionario::getNome)
                    .collect(Collectors.toList());
    
            dto.setFuncionarios(funcionarios);
    
            return dto;
        }).collect(Collectors.toList());
    }
    
}
