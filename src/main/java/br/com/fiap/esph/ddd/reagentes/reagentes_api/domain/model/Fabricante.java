package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fabricante {

    private UUID id;

    private String nomeOficial;
    
    private String nomeFantasia;

    private String cnpj;

    private String paisOrigem;

    private List<Reagente> reagentes = new ArrayList<>() ;



    public Fabricante(String nomeOficial, String nomeFantasia, String cnpj) {
        this.nomeOficial = nomeOficial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public void adicinarReagente (Reagente reagente) {
        getReagentes().add(reagente);
    }

    public void removerReagente (Reagente reagente) {
        getReagentes().remove(reagente);
    }
}
