package br.com.fiap.esph.ddd.reagentes.reagentes_api.service;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.MovimentacaoEstoqueDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.MovimentacaoEstoqueCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.MovimentacaoEstoque;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.TipoMovimentacao;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.repository.MovimentacaoEstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository repository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository repository) {
        this.repository = repository;
    }

    public MovimentacaoEstoqueDTO criar(MovimentacaoEstoqueCreateDTO dto) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setTipo(TipoMovimentacao.valueOf(dto.tipo().toUpperCase()));
        movimentacao.setQuantidadeMovimentada(dto.quantidadeMovimentada());
        movimentacao.setDataHoraMovimentacao(dto.dataHoraMovimentacao());
        movimentacao.setObservacao(dto.observacao());
        repository.save(movimentacao);

        return new MovimentacaoEstoqueDTO(
            movimentacao.getId(),
            movimentacao.getTipo().name(),
            movimentacao.getQuantidadeMovimentada(),
            movimentacao.getDataHoraMovimentacao(),
            movimentacao.getObservacao()
        );
    }

    public List<MovimentacaoEstoqueDTO> listarTodos() {
        return repository.findAll().stream()
                .map(m -> new MovimentacaoEstoqueDTO(
                        m.getId(),
                        m.getTipo().name(),
                        m.getQuantidadeMovimentada(),
                        m.getDataHoraMovimentacao(),
                        m.getObservacao()
                ))
                .toList();
    }

    public MovimentacaoEstoqueDTO buscarPorId(UUID id) {
        MovimentacaoEstoque movimentacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        return new MovimentacaoEstoqueDTO(
                movimentacao.getId(),
                movimentacao.getTipo().name(),
                movimentacao.getQuantidadeMovimentada(),
                movimentacao.getDataHoraMovimentacao(),
                movimentacao.getObservacao()
        );
    }

    public MovimentacaoEstoqueDTO atualizar(UUID id, MovimentacaoEstoqueCreateDTO dto) {
        MovimentacaoEstoque movimentacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        movimentacao.setTipo(TipoMovimentacao.valueOf(dto.tipo().toUpperCase()));
        movimentacao.setQuantidadeMovimentada(dto.quantidadeMovimentada());
        movimentacao.setDataHoraMovimentacao(dto.dataHoraMovimentacao());
        movimentacao.setObservacao(dto.observacao());
        repository.save(movimentacao);

        return new MovimentacaoEstoqueDTO(
                movimentacao.getId(),
                movimentacao.getTipo().name(),
                movimentacao.getQuantidadeMovimentada(),
                movimentacao.getDataHoraMovimentacao(),
                movimentacao.getObservacao()
        );
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}
