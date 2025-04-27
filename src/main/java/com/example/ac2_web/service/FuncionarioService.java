package com.example.ac2_web.service;

import com.example.ac2_web.dto.DadosProjetoDTO;
import com.example.ac2_web.dto.FuncionarioDTO;
import com.example.ac2_web.entity.Funcionario;
import com.example.ac2_web.entity.Projeto;
import com.example.ac2_web.entity.Setor;
import com.example.ac2_web.repository.FuncionarioRepository;
import com.example.ac2_web.repository.ProjetoRepository;
import com.example.ac2_web.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public void adicionar(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());

        Setor setor = setorRepository.findById(dto.getSetorId()).orElseThrow();
        funcionario.setSetor(setor);

        funcionarioRepository.save(funcionario);
    }

    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        List<Projeto> projetos = funcionarioRepository.findProjetosByFuncionarioId(idFuncionario);

        return projetos.stream().map(projeto -> {
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
        }).collect(Collectors.toList());
    }
}
