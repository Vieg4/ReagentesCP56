package br.com.fiap.esph.ddd.reagentes.reagentes_api.service;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.LocalizacaoEstoqueDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.LocalizacaoEstoqueCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.LocalizacaoEstoque;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.mapper.LocalizacaoEstoqueMapper;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.LocalizacaoEstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class LocalizacaoEstoqueService {

    private final LocalizacaoEstoqueRepository repository;

    public LocalizacaoEstoqueService(LocalizacaoEstoqueRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public LocalizacaoEstoqueDTO criar(LocalizacaoEstoqueCreateDTO dto) {
        LocalizacaoEstoque localizacao = new LocalizacaoEstoque();
        localizacao.setCodigoLocal(dto.codigoLocal());
        localizacao.setDescricao(dto.descricao());
        localizacao.setTipo(dto.tipo());
        localizacao.setFaixaTemperaturaNominal(dto.faixaTemperaturaNominal());
        localizacao.setSetor(dto.setor());
        
        repository.save(localizacao);
        return LocalizacaoEstoqueMapper.toDTO(localizacao);
    }

    @Transactional(readOnly = true)
    public List<LocalizacaoEstoqueDTO> listarTodos() {
        return repository.findAll().stream()
                .map(LocalizacaoEstoqueMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LocalizacaoEstoqueDTO buscarPorId(UUID id) {
        LocalizacaoEstoque localizacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
        return LocalizacaoEstoqueMapper.toDTO(localizacao);
    }

    @Transactional
    public LocalizacaoEstoqueDTO atualizar(UUID id, LocalizacaoEstoqueCreateDTO dto) {
        LocalizacaoEstoque localizacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
        
        localizacao.setCodigoLocal(dto.codigoLocal());
        localizacao.setDescricao(dto.descricao());
        localizacao.setTipo(dto.tipo());
        localizacao.setFaixaTemperaturaNominal(dto.faixaTemperaturaNominal());
        localizacao.setSetor(dto.setor());
        
        repository.save(localizacao);
        return LocalizacaoEstoqueMapper.toDTO(localizacao);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Localização não encontrada");
        }
        repository.deleteById(id);
    }
}