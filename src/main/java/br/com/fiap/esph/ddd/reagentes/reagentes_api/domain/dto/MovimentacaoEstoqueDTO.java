package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MovimentacaoEstoqueDTO(
    UUID id,
    String tipo,
    Integer quantidadeMovimentada,
    LocalDateTime dataHoraMovimentacao,
    String observacao
) {}
