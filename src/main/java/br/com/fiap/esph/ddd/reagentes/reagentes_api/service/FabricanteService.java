package br.com.fiap.esph.ddd.reagentes.reagentes_api.service;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.FabricanteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.FabricanteCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Fabricante;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.mapper.FabricanteMapper;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.FabricanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    @Transactional
    public FabricanteDTO criar(FabricanteCreateDTO dto) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNomeOficial(dto.nomeOficial());
        fabricante.setNomeFantasia(dto.nomeFantasia());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setPaisOrigem(dto.paisOrigem());
        
        fabricanteRepository.save(fabricante);
        return FabricanteMapper.toDTO(fabricante);
    }

    @Transactional(readOnly = true)
    public List<FabricanteDTO> listarTodos() {
        return fabricanteRepository.findAll().stream()
                .map(FabricanteMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public FabricanteDTO buscarPorId(UUID id) {
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
        return FabricanteMapper.toDTO(fabricante);
    }

    @Transactional
    public FabricanteDTO atualizar(UUID id, FabricanteCreateDTO dto) {
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
        
        fabricante.setNomeOficial(dto.nomeOficial());
        fabricante.setNomeFantasia(dto.nomeFantasia());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setPaisOrigem(dto.paisOrigem());
        
        fabricanteRepository.save(fabricante);
        return FabricanteMapper.toDTO(fabricante);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!fabricanteRepository.existsById(id)) {
            throw new RuntimeException("Fabricante não encontrado");
        }
        fabricanteRepository.deleteById(id);
    }
}