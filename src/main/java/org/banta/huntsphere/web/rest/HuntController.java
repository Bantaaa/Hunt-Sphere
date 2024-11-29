package org.banta.huntsphere.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.dto.HuntDTO;
import org.banta.huntsphere.service.HuntService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hunts")
public class HuntController {
    private HuntService huntService;

    @PostMapping
    public ResponseEntity<HuntDTO> recordHunt(@Valid @RequestBody HuntDTO huntDTO) {
        return ResponseEntity.ok(huntService.recordHunt(huntDTO));
    }

    @GetMapping("/participation/{participationId}")
    public ResponseEntity<List<HuntDTO>> getParticipationHunts(@PathVariable UUID participationId) {
        return ResponseEntity.ok(huntService.getParticipationHunts(participationId));
    }
}
