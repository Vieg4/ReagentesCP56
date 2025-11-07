package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import java.util.UUID;

public record LocalizacaoEstoqueDTO(
    UUID id,
    String descricao,
    String setor
) {}
