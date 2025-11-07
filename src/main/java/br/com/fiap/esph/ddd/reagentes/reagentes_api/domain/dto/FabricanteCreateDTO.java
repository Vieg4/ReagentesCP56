package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

public record FabricanteCreateDTO(
    String nomeOficial,
    String nomeFantasia,
    String cnpj,
    String paisOrigem
) {}
