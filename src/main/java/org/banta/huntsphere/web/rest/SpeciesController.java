package org.banta.huntsphere.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.domain.enums.SpeciesType;
import org.banta.huntsphere.dto.SpeciesDTO;
import org.banta.huntsphere.service.SpeciesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/species")
public class SpeciesController {
    private SpeciesService speciesService;

    @PostMapping
    public ResponseEntity<SpeciesDTO> createSpecies(@Valid @RequestBody SpeciesDTO speciesDTO) {
        return ResponseEntity.ok(speciesService.createSpecies(speciesDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpeciesDTO> updateSpecies(@PathVariable UUID id, @Valid @RequestBody SpeciesDTO speciesDTO) {
        return ResponseEntity.ok(speciesService.updateSpecies(id, speciesDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeciesDTO> getSpecies(@PathVariable UUID id) {
        return ResponseEntity.ok(speciesService.getSpeciesById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<SpeciesDTO>> getByType(@PathVariable SpeciesType type) {
        return ResponseEntity.ok(speciesService.getAllSpeciesByType(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable UUID id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.noContent().build();
    }
}
