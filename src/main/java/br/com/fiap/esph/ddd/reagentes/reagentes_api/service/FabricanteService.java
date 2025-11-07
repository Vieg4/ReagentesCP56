package br.com.fiap.esph.ddd.reagentes.reagentes_api.service;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.FabricanteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.FabricanteCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Fabricante;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.FabricanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public FabricanteDTO criar(FabricanteCreateDTO dto) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNomeOficial(dto.nomeOficial());
        fabricante.setNomeFantasia(dto.nomeFantasia());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setPaisOrigem(dto.paisOrigem());
        fabricanteRepository.save(fabricante);
        return new FabricanteDTO(fabricante.getId(), fabricante.getNomeOficial(), fabricante.getNomeFantasia(), fabricante.getCnpj(), fabricante.getPaisOrigem());
    }

    public List<FabricanteDTO> listarTodos() {
        return fabricanteRepository.findAll().stream()
                .map(f -> new FabricanteDTO(f.getId(), f.getNomeOficial(), f.getNomeFantasia(), f.getCnpj(), f.getPaisOrigem()))
                .toList();
    }

    public FabricanteDTO buscarPorId(UUID id) {
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
        return new FabricanteDTO(fabricante.getId(), fabricante.getNomeOficial(), fabricante.getNomeFantasia(), fabricante.getCnpj(), fabricante.getPaisOrigem());
    }

    public FabricanteDTO atualizar(UUID id, FabricanteCreateDTO dto) {
        Fabricante fabricante = fabricanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
        fabricante.setNomeOficial(dto.nomeOficial());
        fabricante.setNomeFantasia(dto.nomeFantasia());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setPaisOrigem(dto.paisOrigem());
        fabricanteRepository.save(fabricante);
        return new FabricanteDTO(fabricante.getId(), fabricante.getNomeOficial(), fabricante.getNomeFantasia(), fabricante.getCnpj(), fabricante.getPaisOrigem());
    }

    public void deletar(UUID id) {
        fabricanteRepository.deleteById(id);
    }
}
