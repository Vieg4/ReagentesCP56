package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Fabricante {

    private UUID id;

    private String nomeOficial;
    
    private String nomeFantasia;

    private String cnpj;

    private String paisOrigem;

    private List<Reagente> reagentes = new ArrayList<>() ;



    public void adicinarReagente (Reagente reagente) {

    }

    public void removerReagente (Reagente reagente) {

    }
}
