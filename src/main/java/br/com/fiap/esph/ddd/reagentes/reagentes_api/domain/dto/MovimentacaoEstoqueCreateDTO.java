package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import java.time.LocalDateTime;

public record MovimentacaoEstoqueCreateDTO(
    String tipo,
    Integer quantidadeMovimentada,
    LocalDateTime dataHoraMovimentacao,
    String observacao
) {}
