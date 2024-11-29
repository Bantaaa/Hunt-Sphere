package org.banta.huntsphere.web.rest;

import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.dto.ParticipationDTO;
import org.banta.huntsphere.service.ParticipationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participations")
public class ParticipationController {
    private ParticipationService participationService;

    @PostMapping
    public ResponseEntity<ParticipationDTO> register(@RequestParam UUID userId, @RequestParam UUID competitionId) {
        return ResponseEntity.ok(participationService.registerParticipation(userId, competitionId));
    }

    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<ParticipationDTO>> getResults(@PathVariable UUID competitionId) {
        return ResponseEntity.ok(participationService.getCompetitionResults(competitionId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ParticipationDTO>> getUserParticipations(@PathVariable UUID userId) {
        return ResponseEntity.ok(participationService.getUserParticipations(userId));
    }
}
