package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reagentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Reagente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String codigoSku;

    @Column(nullable = false)
    private String lote;

    @Column(nullable = false)
    private LocalDate dataValidade;

    @Column(nullable = false)
    private LocalDate dataRecebimento;

    @Column(nullable = false)
    private Integer quantidadeEmEstoque;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReagente status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabricante_id", nullable = false)
    private Fabricante fabricante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacao_estoque_id", nullable = false)
    private LocalizacaoEstoque localizacaoEstoque;

    @OneToMany(mappedBy = "reagente", cascade = CascadeType.ALL, orphanRemoval = true)
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