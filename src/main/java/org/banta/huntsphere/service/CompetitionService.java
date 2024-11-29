package org.banta.huntsphere.service;

import org.banta.huntsphere.dto.CompetitionDTO;

import java.util.List;
import java.util.UUID;

public interface CompetitionService {
    CompetitionDTO createCompetition(CompetitionDTO competitionDTO);
    CompetitionDTO updateCompetition(UUID id, CompetitionDTO competitionDTO);
    void deleteCompetition(UUID id);
    CompetitionDTO getCompetitionById(UUID id);
    List<CompetitionDTO> getActiveCompetitions();
}
