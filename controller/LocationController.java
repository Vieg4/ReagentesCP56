package com.laboratorio.inventario.controller;

import com.laboratorio.inventario.dto.LocationCreationDTO;
import com.laboratorio.inventario.dto.LocationDTO;
import com.laboratorio.inventario.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// Importante: Assumimos que o LocationService, LocationDTO e LocationCreationDTO existem.
@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // CRIAÇÃO (POST) -> Status 201 criado
    @PostMapping
    public ResponseEntity<LocationDTO> create(@RequestBody LocationCreationDTO creationDTO) {
        LocationDTO createdLocation = locationService.create(creationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLocation.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdLocation);
    }

    // RECUPERAÇÃO - Listar Todos (GET) -> Status 200 ok
    @GetMapping
    public ResponseEntity<List<LocationDTO>> findAll() {
        List<LocationDTO> locations = locationService.findAll();
        return ResponseEntity.ok(locations); 
    }

    // RECUPERAÇÃO - Por ID (GET) -> Status 200 ok / 404 Nao encontrado
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> findById(@PathVariable Long id) {
        LocationDTO locationDTO = locationService.findById(id);
        return ResponseEntity.ok(locationDTO);
    }

    // ATUALIZAÇÃO (PUT) -> Status 200 OK / 404 Nao encontrado
    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> update(@PathVariable Long id, @RequestBody LocationCreationDTO creationDTO) {
        LocationDTO updatedLocation = locationService.update(id, creationDTO);
        return ResponseEntity.ok(updatedLocation); 
    }

    // EXCLUSÃO (DELETE) -> Status 204 Sem conteudo / 404 Nao encontrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}