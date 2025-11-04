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

public class Fabricante {

    private UUID id;

    private String nomeOficial;

    private String nomeFantasia;

    private String cnpj;
    
    private String paisOrigem;

    @Builder.Default
    private List<Reagente> reagentes = new ArrayList<>();

    public Fabricante(String nomeOficial, String nomeFantasia, String cnpj) {
        this.nomeOficial = nomeOficial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }


    public void adicionarReagente(Reagente reagente) {
        if (reagente == null) return;

        reagentes.add(reagente);
        reagente.setFabricante(this);
    }


    public void removerReagente(Reagente reagente) {
        if (reagente == null) return;

        reagentes.remove(reagente);
        reagente.setFabricante(null);
    }
}
