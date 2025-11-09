package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fabricantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nomeOficial;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false)
    private String paisOrigem;

    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL, orphanRemoval = true)
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