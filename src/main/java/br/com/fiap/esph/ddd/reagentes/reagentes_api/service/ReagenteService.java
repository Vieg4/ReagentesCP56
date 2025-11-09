package br.com.fiap.esph.ddd.reagentes.reagentes_api.service;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.ReagenteCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.ReagenteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Fabricante;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.LocalizacaoEstoque;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Reagente;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.mapper.ReagenteMapper;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.FabricanteRepository;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.LocalizacaoEstoqueRepository;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.ReagenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReagenteService {

    private final ReagenteRepository reagenteRepository;
    private final FabricanteRepository fabricanteRepository;
    private final LocalizacaoEstoqueRepository localizacaoEstoqueRepository;

    public ReagenteService(ReagenteRepository reagenteRepository, 
                          FabricanteRepository fabricanteRepository,
                          LocalizacaoEstoqueRepository localizacaoEstoqueRepository) {
        this.reagenteRepository = reagenteRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.localizacaoEstoqueRepository = localizacaoEstoqueRepository;
    }

    @Transactional
    public ReagenteDTO create(ReagenteCreateDTO dto) {
        // Buscar Fabricante
        Fabricante fabricante = fabricanteRepository.findById(dto.fabricanteId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Fabricante não encontrado com id: " + dto.fabricanteId()));
        
        // Buscar LocalizacaoEstoque
        LocalizacaoEstoque localizacao = localizacaoEstoqueRepository.findById(dto.localizacaoEstoqueId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Localização de estoque não encontrada com id: " + dto.localizacaoEstoqueId()));
        
        // Criar Reagente
        Reagente reagente = new Reagente();
        reagente.setNome(dto.nome());
        reagente.setCodigoSku(dto.codigoSku());
        reagente.setLote(dto.lote());
        reagente.setDataValidade(dto.dataValidade());
        reagente.setDataRecebimento(dto.dataRecebimento());
        reagente.setQuantidadeEmEstoque(dto.quantidadeEmEstoque());
        reagente.setStatus(dto.status());
        reagente.setFabricante(fabricante);
        reagente.setLocalizacaoEstoque(localizacao);

        reagenteRepository.save(reagente);
        
        // Usar Mapper para converter para DTO
        return ReagenteMapper.toDTO(reagente);
    }

    @Transactional(readOnly = true)
    public List<ReagenteDTO> findAll() {
        return reagenteRepository.findAll().stream()
                .map(ReagenteMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReagenteDTO findById(UUID id) {
        Reagente reagente = reagenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Reagente não encontrado com id: " + id));
        return ReagenteMapper.toDTO(reagente);
    }

    @Transactional
    public ReagenteDTO update(UUID id, ReagenteCreateDTO dto) {
        Reagente reagente = reagenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Reagente não encontrado com id: " + id));

        // Buscar Fabricante
        Fabricante fabricante = fabricanteRepository.findById(dto.fabricanteId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Fabricante não encontrado com id: " + dto.fabricanteId()));
        
        // Buscar LocalizacaoEstoque
        LocalizacaoEstoque localizacao = localizacaoEstoqueRepository.findById(dto.localizacaoEstoqueId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Localização de estoque não encontrada com id: " + dto.localizacaoEstoqueId()));

        // Atualizar campos
        reagente.setNome(dto.nome());
        reagente.setCodigoSku(dto.codigoSku());
        reagente.setLote(dto.lote());
        reagente.setDataValidade(dto.dataValidade());
        reagente.setDataRecebimento(dto.dataRecebimento());
        reagente.setQuantidadeEmEstoque(dto.quantidadeEmEstoque());
        reagente.setStatus(dto.status());
        reagente.setFabricante(fabricante);
        reagente.setLocalizacaoEstoque(localizacao);

        reagenteRepository.save(reagente);
        return ReagenteMapper.toDTO(reagente);
    }

    @Transactional
    public void delete(UUID id) {
        if (!reagenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Reagente não encontrado com id: " + id);
        }
        reagenteRepository.deleteById(id);
    }
}