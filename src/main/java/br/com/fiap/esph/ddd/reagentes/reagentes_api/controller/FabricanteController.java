package br.com.fiap.esph.ddd.reagentes.reagentes_api.controller;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.FabricanteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.FabricanteCreateDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.service.FabricanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fabricantes")
public class FabricanteController {

    private final FabricanteService fabricanteService;

    public FabricanteController(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @PostMapping
    public ResponseEntity<FabricanteDTO> criar(@RequestBody FabricanteCreateDTO dto) {
        FabricanteDTO criado = fabricanteService.criar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criado.id())
                .toUri();
        return ResponseEntity.created(uri).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<FabricanteDTO>> listarTodos() {
        return ResponseEntity.ok(fabricanteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(fabricanteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricanteDTO> atualizar(@PathVariable UUID id, @RequestBody FabricanteCreateDTO dto) {
        return ResponseEntity.ok(fabricanteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        fabricanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
