package br.com.fiap.esph.ddd.reagentes.reagentes_api.service;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.ReagenteCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.ReagenteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Fabricante;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Reagente;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.FabricanteRepository;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.ReagenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReagenteService {

    private final ReagenteRepository reagenteRepository;
    private final FabricanteRepository fabricanteRepository;

    public ReagenteService(ReagenteRepository reagenteRepository, FabricanteRepository fabricanteRepository) {
        this.reagenteRepository = reagenteRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    // ========================
    // CRIAR REAGENTE (POST)
    // ========================
    public ReagenteDTO create(ReagenteCreateDTO dto) {
        Reagente reagente = new Reagente();

        reagente.setNome(dto.nome());
        reagente.setCodigoSku(dto.codigoSku());
        reagente.setLote(dto.lote());
        reagente.setDataValidade(dto.dataValidade());
        reagente.setDataRecebimento(dto.dataRecebimento());
        reagente.setQuantidadeEmEstoque(dto.quantidadeEmEstoque());
        reagente.setStatus(dto.status());

        if (dto.fabricanteId() != null) {
            Fabricante fabricante = fabricanteRepository.findById(dto.fabricanteId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Fabricante não encontrado com id: " + dto.fabricanteId()));
            reagente.setFabricante(fabricante);
        }

        reagenteRepository.save(reagente);
        return toDTO(reagente);
    }

    // ========================
    // LISTAR TODOS (GET)
    // ========================
    public List<ReagenteDTO> findAll() {
        return reagenteRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    // ========================
    // BUSCAR POR ID (GET /{id})
    // ========================
    public ReagenteDTO findById(UUID id) {
        Reagente reagente = reagenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Reagente não encontrado com id: " + id));
        return toDTO(reagente);
    }

    // ========================
    // ATUALIZAR (PUT /{id})
    // ========================
    public ReagenteDTO update(UUID id, ReagenteCreateDTO dto) {
        Reagente reagente = reagenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Reagente não encontrado com id: " + id));

        reagente.setNome(dto.nome());
        reagente.setCodigoSku(dto.codigoSku());
        reagente.setLote(dto.lote());
        reagente.setDataValidade(dto.dataValidade());
        reagente.setDataRecebimento(dto.dataRecebimento());
        reagente.setQuantidadeEmEstoque(dto.quantidadeEmEstoque());
        reagente.setStatus(dto.status());

        if (dto.fabricanteId() != null) {
            Fabricante fabricante = fabricanteRepository.findById(dto.fabricanteId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Fabricante não encontrado com id: " + dto.fabricanteId()));
            reagente.setFabricante(fabricante);
        }

        reagenteRepository.save(reagente);
        return toDTO(reagente);
    }

    // ========================
    // DELETAR (DELETE /{id})
    // ========================
    public void delete(UUID id) {
        if (!reagenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Reagente não encontrado com id: " + id);
        }
        reagenteRepository.deleteById(id);
    }

    // ========================
    // CONVERSOR PARA DTO
    // ========================
    private ReagenteDTO toDTO(Reagente reagente) {
        return new ReagenteDTO(
                reagente.getId(),
                reagente.getNome(),
                reagente.getCodigoSku(),
                reagente.getLote(),
                reagente.getDataValidade(),
                reagente.getDataRecebimento(),
                reagente.getQuantidadeEmEstoque(),
                reagente.getStatus(),
                reagente.getFabricante() != null
                        ? reagente.getFabricante().getNomeFantasia()
                        : null
        );
    }
}
