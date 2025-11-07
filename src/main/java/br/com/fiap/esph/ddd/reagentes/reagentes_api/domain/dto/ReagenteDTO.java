package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.StatusReagente;

public record ReagenteDTO(
        UUID id,
        String nome,
        String codigoSku,
        String lote,
        LocalDate dataValidade,
        LocalDate dataRecebimento,
        Integer quantidadeEmEstoque,
        StatusReagente status,
        String nomeFabricante
) {}
