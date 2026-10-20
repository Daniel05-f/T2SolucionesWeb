package com.lezama_perez.t2soluciones.controller;

import com.lezama_perez.t2soluciones.dto.TourPackageRequestDTO;
import com.lezama_perez.t2soluciones.dto.TourPackageResponseDTO;
import com.lezama_perez.t2soluciones.model.TourPackage;
import com.lezama_perez.t2soluciones.service.implement.ITourPackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tour-packages")
@RequiredArgsConstructor
public class TourPackageController {

    private final ITourPackageService service;

    // 1. GET (Listar todos) -> 200 OK
    @GetMapping
    public ResponseEntity<List<TourPackageResponseDTO>> getAll() throws Exception {
        List<TourPackageResponseDTO> list = service.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // 2. GET (Buscar por ID) -> 200 OK o 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<TourPackageResponseDTO> getById(@PathVariable Integer id) throws Exception {
        TourPackage entity = service.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(convertToDTO(entity)); // 200
    }

    // 3. POST (Crear) -> 201 Created o 400 Bad Request
    @PostMapping
    public ResponseEntity<TourPackageResponseDTO> create(@Valid @RequestBody TourPackageRequestDTO dto) throws Exception {
        TourPackage entity = convertToEntity(dto);
        TourPackage saved = service.save(entity);
        return new ResponseEntity<>(convertToDTO(saved), HttpStatus.CREATED); // 201
    }

    // 4. PUT (Actualizar) -> 200 OK o 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<TourPackageResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody TourPackageRequestDTO dto) {
        try {
            TourPackage entity = convertToEntity(dto);
            entity.setId(id); // Aseguramos que se actualice el ID correcto
            TourPackage updated = service.update(id, entity);
            return ResponseEntity.ok(convertToDTO(updated)); // 200
        } catch (Exception e) {
            // Si el ID no existe, el GenericService lanza una Exception
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // 5. DELETE (Eliminar) -> 204 No Content o 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            // Verificamos si existe antes de borrar para lanzar el 404
            if (service.findById(id) == null) {
                return ResponseEntity.notFound().build(); // 404
            }
            service.delete(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    // --- MÉTODOS DE MAPEO MANUAL (Entidad <-> DTO) ---

    private TourPackageResponseDTO convertToDTO(TourPackage entity) {
        TourPackageResponseDTO dto = new TourPackageResponseDTO();
        dto.setId(entity.getId());
        dto.setDestination(entity.getDestination());
        dto.setDays(entity.getDays());
        dto.setPrice(entity.getPrice());
        dto.setIncludesFlight(entity.getIncludesFlight());
        dto.setAvailableSlots(entity.getAvailableSlots());
        return dto;
    }

    private TourPackage convertToEntity(TourPackageRequestDTO dto) {
        TourPackage entity = new TourPackage();
        entity.setDestination(dto.getDestination());
        entity.setDays(dto.getDays());
        entity.setPrice(dto.getPrice());
        entity.setIncludesFlight(dto.getIncludesFlight());
        entity.setAvailableSlots(dto.getAvailableSlots());
        return entity;
    }
}