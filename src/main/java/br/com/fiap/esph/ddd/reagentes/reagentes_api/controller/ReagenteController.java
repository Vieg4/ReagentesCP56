package br.com.fiap.esph.ddd.reagentes.reagentes_api.controller;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.ReagenteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.service.ReagenteService;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.ReagenteCreateDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reagentes")
public class ReagenteController {

    private final ReagenteService service;

    public ReagenteController(ReagenteService service) {
        this.service = service;
    }

    // ==========================
    // b) OPERAÇÃO DE CRIAÇÃO (POST)
    // ==========================
    @PostMapping
    public ResponseEntity<ReagenteDTO> create(@RequestBody ReagenteCreateDTO dto) {
        ReagenteDTO created = service.create(dto);

        // Cria a URI de retorno para o novo recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        // Retorna 201 (CREATED)
        return ResponseEntity.created(uri).body(created);
    }

    // ==========================
    // c) OPERAÇÃO DE LISTAGEM (GET)
    // ==========================
    @GetMapping
    public ResponseEntity<List<ReagenteDTO>> findAll() {
        List<ReagenteDTO> reagentes = service.findAll();
        return ResponseEntity.ok(reagentes); // HTTP 200
    }

    // ==========================
    // c) OPERAÇÃO DE CONSULTA POR ID (GET /{id})
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<ReagenteDTO> findById(@PathVariable UUID id) {
        ReagenteDTO reagente = service.findById(id);
        return ResponseEntity.ok(reagente); // HTTP 200 se encontrado
    }

    // ==========================
    // OPERAÇÃO DE ATUALIZAÇÃO (PUT)
    // ==========================
    @PutMapping("/{id}")
    public ResponseEntity<ReagenteDTO> update(@PathVariable UUID id, @RequestBody ReagenteCreateDTO dto) {
        ReagenteDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated); // HTTP 200 se atualizado
    }

    // ==========================
    // OPERAÇÃO DE EXCLUSÃO (DELETE)
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 se removido com sucesso
    }
}
