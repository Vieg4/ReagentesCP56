package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import java.util.UUID;

public record FabricanteDTO(
    UUID id,
    String nomeOficial,
    String nomeFantasia,
    String cnpj,
    String paisOrigem
) {}
