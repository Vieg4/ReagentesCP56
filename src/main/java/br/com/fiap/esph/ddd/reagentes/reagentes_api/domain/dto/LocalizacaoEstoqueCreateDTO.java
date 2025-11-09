package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.TipoLocalizacaoEstoque;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocalizacaoEstoqueCreateDTO(
    @NotBlank(message = "Código local é obrigatório")
    String codigoLocal,
    
    @NotBlank(message = "Descrição é obrigatória")
    String descricao,
    
    @NotNull(message = "Tipo é obrigatório")
    TipoLocalizacaoEstoque tipo,
    
    @NotBlank(message = "Faixa de temperatura é obrigatória")
    String faixaTemperaturaNominal,
    
    @NotBlank(message = "Setor é obrigatório")
    String setor
) {}