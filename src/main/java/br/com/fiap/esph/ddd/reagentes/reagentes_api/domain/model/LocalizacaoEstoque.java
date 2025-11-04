package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class LocalizacaoEstoque {

    private UUID id;

    private String codigoLocal;

    private String descricao;

    private String setor;

    private String faixaTemperaturaNominal;

    private TipoLocalizacaoEstoque tipo;

    @Builder.Default
    private List<Reagente> reagentes = new ArrayList<>();

    public LocalizacaoEstoque(String codigoLocal, String descricao, String setor, String faixaTemperaturaNominal, TipoLocalizacaoEstoque tipo) {
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
