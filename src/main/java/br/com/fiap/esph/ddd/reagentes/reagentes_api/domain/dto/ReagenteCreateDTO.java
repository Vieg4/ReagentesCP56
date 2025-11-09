package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.StatusReagente;
import jakarta.validation.constraints.*;

public record ReagenteCreateDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        
        @NotBlank(message = "Código SKU é obrigatório")
        String codigoSku,
        
        @NotBlank(message = "Lote é obrigatório")
        String lote,
        
        @NotNull(message = "Data de validade é obrigatória")
        @Future(message = "Data de validade deve ser futura")
        LocalDate dataValidade,
        
        @NotNull(message = "Data de recebimento é obrigatória")
        @PastOrPresent(message = "Data de recebimento não pode ser futura")
        LocalDate dataRecebimento,
        
        @NotNull(message = "Quantidade em estoque é obrigatória")
        @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer quantidadeEmEstoque,
        
        @NotNull(message = "Status é obrigatório")
        StatusReagente status,
        
        @NotNull(message = "Fabricante é obrigatório")
        UUID fabricanteId,
        
        @NotNull(message = "Localização de estoque é obrigatória")
        UUID localizacaoEstoqueId
) {}