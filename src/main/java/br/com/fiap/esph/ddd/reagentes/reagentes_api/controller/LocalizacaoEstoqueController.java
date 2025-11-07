package br.com.fiap.esph.ddd.reagentes.reagentes_api.controller;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.LocalizacaoEstoqueDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.LocalizacaoEstoqueCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.service.LocalizacaoEstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoEstoqueController {

    private final LocalizacaoEstoqueService localizacaoService;

    public LocalizacaoEstoqueController(LocalizacaoEstoqueService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @PostMapping
    public ResponseEntity<LocalizacaoEstoqueDTO> criar(@RequestBody LocalizacaoEstoqueCreateDTO dto) {
        LocalizacaoEstoqueDTO criado = localizacaoService.criar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criado.id())
                .toUri();
        return ResponseEntity.created(uri).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<LocalizacaoEstoqueDTO>> listarTodos() {
        return ResponseEntity.ok(localizacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoEstoqueDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(localizacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalizacaoEstoqueDTO> atualizar(@PathVariable UUID id, @RequestBody LocalizacaoEstoqueCreateDTO dto) {
        return ResponseEntity.ok(localizacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        localizacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
