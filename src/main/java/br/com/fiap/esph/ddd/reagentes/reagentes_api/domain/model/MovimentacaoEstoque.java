package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder

public class MovimentacaoEstoque {

    private UUID id;

    private String observacao;

    private Integer quantidadeMovimentada;

    private LocalDateTime dataHoraMovimentacao;

    private LocalDate dataPagamento;

    private TipoMovimentacao tipo;

    private Reagente reagente;

}
