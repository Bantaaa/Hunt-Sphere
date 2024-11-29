package org.banta.huntsphere.service;

import org.banta.huntsphere.dto.ParticipationDTO;

import java.util.List;
import java.util.UUID;

public interface ParticipationService {
    ParticipationDTO registerParticipation(UUID userId, UUID competitionId);
    ParticipationDTO updateScore(UUID id, Double score);
    List<ParticipationDTO> getCompetitionResults(UUID competitionId);
    List<ParticipationDTO> getUserParticipations(UUID userId);
}
