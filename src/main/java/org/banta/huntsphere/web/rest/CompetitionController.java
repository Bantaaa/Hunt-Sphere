package org.banta.huntsphere.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.dto.CompetitionDTO;
import org.banta.huntsphere.service.CompetitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/competitions")
public class CompetitionController {
    private CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<CompetitionDTO> createCompetition(@Valid @RequestBody CompetitionDTO competitionDTO) {
        return ResponseEntity.ok(competitionService.createCompetition(competitionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDTO> getCompetition(@PathVariable UUID id) {
        return ResponseEntity.ok(competitionService.getCompetitionById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<CompetitionDTO>> getActiveCompetitions() {
        return ResponseEntity.ok(competitionService.getActiveCompetitions());
    }
}