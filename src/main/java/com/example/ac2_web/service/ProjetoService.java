package com.example.ac2_web.service;

import com.example.ac2_web.dto.DadosProjetoDTO;
import com.example.ac2_web.dto.ProjetoDTO;
import com.example.ac2_web.entity.Funcionario;
import com.example.ac2_web.entity.Projeto;
import com.example.ac2_web.repository.FuncionarioRepository;
import com.example.ac2_web.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void adicionar(ProjetoDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());
        projetoRepository.save(projeto);
    }

    public DadosProjetoDTO buscarPorId(Integer id) {
        Projeto projeto = projetoRepository.findProjetoWithFuncionarios(id);

        DadosProjetoDTO dto = new DadosProjetoDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDataInicio(projeto.getDataInicio());
        dto.setDataFim(projeto.getDataFim());

        List<String> nomes = projeto.getFuncionarios()
                .stream()
                .map(Funcionario::getNome)
                .collect(Collectors.toList());

        dto.setFuncionarios(nomes);
        return dto;
    }

    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto).orElseThrow();
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElseThrow();

        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }

    public List<DadosProjetoDTO> buscarProjetosPorPeriodo(LocalDate inicio, LocalDate fim) {
        List<Projeto> projetos = projetoRepository.findByDataInicioBetween(inicio, fim);

        return projetos.stream().map(projeto -> {
            DadosProjetoDTO dto = new DadosProjetoDTO();
            dto.setId(projeto.getId());
            dto.setNome(projeto.getNome());
            dto.setDataInicio(projeto.getDataInicio());
            dto.setDataFim(projeto.getDataFim());

            List<String> nomesFuncionarios = projeto.getFuncionarios()
                    .stream()
                    .map(funcionario -> funcionario.getNome())
                    .collect(Collectors.toList());

            dto.setFuncionarios(nomesFuncionarios);

            return dto;
        }).collect(Collectors.toList());
    }
}
