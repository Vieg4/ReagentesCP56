package br.com.fiap.esph.ddd.reagentes.reagentes_api.repository;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, UUID> {
}
