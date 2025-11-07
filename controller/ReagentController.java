package com.laboratorio.inventario.controller;

import com.laboratorio.inventario.dto.ReagentCreationDTO;
import com.laboratorio.inventario.dto.ReagentDTO;
import com.laboratorio.inventario.service.ReagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reagents")
public class ReagentController {

    private final ReagentService reagentService;

    @Autowired
    public ReagentController(ReagentService reagentService) {
        this.reagentService = reagentService;
    }

    // CRIAÇÃO (POST) -> Status 201 criado
    @PostMapping
    public ResponseEntity<ReagentDTO> create(@RequestBody ReagentCreationDTO creationDTO) {
        ReagentDTO createdReagent = reagentService.create(creationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReagent.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdReagent);
    }

    // RECUPERAÇÃO - Listar Todos (GET) -> Status 200 ok
    @GetMapping
    public ResponseEntity<List<ReagentDTO>> findAll() {
        List<ReagentDTO> reagents = reagentService.findAll();
        return ResponseEntity.ok(reagents); 
    }

    // RECUPERAÇÃO - Por ID (GET com Path Parameter) -> Status 200 ok / 404 Nao encontrado
    @GetMapping("/{id}")
    public ResponseEntity<ReagentDTO> findById(@PathVariable Long id) {
        ReagentDTO reagentDTO = reagentService.findById(id);
        return ResponseEntity.ok(reagentDTO);
    }

    // ATUALIZAÇÃO (PUT) -> Status 200 ok / 404 Nao encontrado
    @PutMapping("/{id}")
    public ResponseEntity<ReagentDTO> update(@PathVariable Long id, @RequestBody ReagentCreationDTO creationDTO) {
        ReagentDTO updatedReagent = reagentService.update(id, creationDTO);
        return ResponseEntity.ok(updatedReagent); 
    }

    // EXCLUSÃO (DELETE) -> Status 204 Sem conteudo / 404 Nao encontrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reagentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}