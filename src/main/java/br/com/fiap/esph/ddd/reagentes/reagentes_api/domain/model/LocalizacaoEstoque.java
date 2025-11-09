package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "localizacoes_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class LocalizacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String codigoLocal;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String setor;

    @Column(nullable = false)
    private String faixaTemperaturaNominal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLocalizacaoEstoque tipo;

    @OneToMany(mappedBy = "localizacaoEstoque", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Reagente> reagentes = new ArrayList<>();

    public LocalizacaoEstoque(String codigoLocal, String descricao, String setor, 
                             String faixaTemperaturaNominal, TipoLocalizacaoEstoque tipo) {
        this.codigoLocal = codigoLocal;
        this.descricao = descricao;
        this.setor = setor;
        this.faixaTemperaturaNominal = faixaTemperaturaNominal;
        this.tipo = tipo;
    }

    public void adicionarReagente(Reagente reagente) {
        if (reagente == null) return;
        reagentes.add(reagente);
        reagente.setLocalizacaoEstoque(this);
    }

    public void removerReagente(Reagente reagente) {
        if (reagente == null) return;
        reagentes.remove(reagente);
        reagente.setLocalizacaoEstoque(null);
    }
}