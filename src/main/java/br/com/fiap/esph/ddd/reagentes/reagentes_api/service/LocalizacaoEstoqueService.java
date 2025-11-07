package br.com.fiap.esph.ddd.reagentes.reagentes_api.service;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.LocalizacaoEstoqueDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.LocalizacaoEstoqueCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.LocalizacaoEstoque;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.LocalizacaoEstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalizacaoEstoqueService {

    private final LocalizacaoEstoqueRepository repository;

    public LocalizacaoEstoqueService(LocalizacaoEstoqueRepository repository) {
        this.repository = repository;
    }

    public LocalizacaoEstoqueDTO criar(LocalizacaoEstoqueCreateDTO dto) {
        LocalizacaoEstoque localizacao = new LocalizacaoEstoque();
        localizacao.setDescricao(dto.descricao());
        localizacao.setSetor(dto.setor());
        repository.save(localizacao);
        return new LocalizacaoEstoqueDTO(localizacao.getId(), localizacao.getDescricao(), localizacao.getSetor());
    }

    public List<LocalizacaoEstoqueDTO> listarTodos() {
        return repository.findAll().stream()
                .map(l -> new LocalizacaoEstoqueDTO(l.getId(), l.getDescricao(), l.getSetor()))
                .toList();
    }

    public LocalizacaoEstoqueDTO buscarPorId(UUID id) {
        LocalizacaoEstoque localizacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
        return new LocalizacaoEstoqueDTO(localizacao.getId(), localizacao.getDescricao(), localizacao.getSetor());
    }

    public LocalizacaoEstoqueDTO atualizar(UUID id, LocalizacaoEstoqueCreateDTO dto) {
        LocalizacaoEstoque localizacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
        localizacao.setDescricao(dto.descricao());
        localizacao.setSetor(dto.setor());
        repository.save(localizacao);
        return new LocalizacaoEstoqueDTO(localizacao.getId(), localizacao.getDescricao(), localizacao.getSetor());
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}
