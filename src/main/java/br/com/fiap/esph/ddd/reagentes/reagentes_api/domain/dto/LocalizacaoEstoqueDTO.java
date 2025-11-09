package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import java.util.UUID;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.TipoLocalizacaoEstoque;

public record LocalizacaoEstoqueDTO(
        UUID id,
        String codigoLocal,
        String descricao,
        TipoLocalizacaoEstoque tipo,
        String faixaTemperaturaNominal,
        String setor
) {}
