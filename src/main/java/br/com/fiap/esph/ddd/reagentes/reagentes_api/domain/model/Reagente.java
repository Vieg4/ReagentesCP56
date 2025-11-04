package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder

public class Reagente {

    private UUID id;

    private String nome;

    private String codigoSku;

    private String lote;

    private LocalDate dataValidade;

    private LocalDate dataRecebimento;

    private Integer quantidadeEmEstoque;

    private StatusReagente status;

    private Fabricante fabricante;

    private LocalizacaoEstoque localizacaoEstoque;

    @Builder.Default
    private List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

    public void movimentarEstoque(MovimentacaoEstoque movimentacao) {
        if (movimentacao == null) return;

        movimentacoes.add(movimentacao);
        movimentacao.setReagente(this);

        if (movimentacao.getQuantidadeMovimentada() != null) {
            this.quantidadeEmEstoque = 
                (this.quantidadeEmEstoque == null ? 0 : this.quantidadeEmEstoque) 
                + movimentacao.getQuantidadeMovimentada();
        }
    }


    public boolean estaVencido() {
        if (dataValidade == null) return false;
        return dataValidade.isBefore(LocalDate.now());
    }
}
