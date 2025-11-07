package com.laboratorio.inventario.controller;

import com.laboratorio.inventario.dto.SupplierCreationDTO;
import com.laboratorio.inventario.dto.SupplierDTO;
import com.laboratorio.inventario.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// Importante: Assumimos que o SupplierService, SupplierDTO e SupplierCreationDTO existem.
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // CRIAÇÃO (POST) -> Status 201 Created
    @PostMapping
    public ResponseEntity<SupplierDTO> create(@RequestBody SupplierCreationDTO creationDTO) {
        SupplierDTO createdSupplier = supplierService.create(creationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdSupplier.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdSupplier);
    }

    // RECUPERAÇÃO - Listar Todos (GET) -> Status 200 ok
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findAll() {
        List<SupplierDTO> suppliers = supplierService.findAll();
        return ResponseEntity.ok(suppliers); 
    }

    // RECUPERAÇÃO - Por ID (GET) -> Status 200 ok / 404 Nao encontrado
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable Long id) {
        SupplierDTO supplierDTO = supplierService.findById(id);
        return ResponseEntity.ok(supplierDTO);
    }

    // ATUALIZAÇÃO (PUT) -> Status 200 ok / 404 Nao encontrado
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable Long id, @RequestBody SupplierCreationDTO creationDTO) {
        SupplierDTO updatedSupplier = supplierService.update(id, creationDTO);
        return ResponseEntity.ok(updatedSupplier); 
    }

    // EXCLUSÃO (DELETE) -> Status 204 Sem conteudo / 404 Nao encontrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}