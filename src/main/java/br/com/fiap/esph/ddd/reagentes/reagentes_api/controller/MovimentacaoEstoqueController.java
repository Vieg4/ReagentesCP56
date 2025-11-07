package br.com.fiap.esph.ddd.reagentes.reagentes_api.controller;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.MovimentacaoEstoqueDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.MovimentacaoEstoqueCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.service.MovimentacaoEstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoEstoqueDTO> criar(@RequestBody MovimentacaoEstoqueCreateDTO dto) {
        MovimentacaoEstoqueDTO criada = movimentacaoService.criar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criada.id())
                .toUri();
        return ResponseEntity.created(uri).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> listarTodos() {
        return ResponseEntity.ok(movimentacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(movimentacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueDTO> atualizar(@PathVariable UUID id, @RequestBody MovimentacaoEstoqueCreateDTO dto) {
        return ResponseEntity.ok(movimentacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        movimentacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
