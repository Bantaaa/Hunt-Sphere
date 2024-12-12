package org.banta.huntsphere.service;

import org.banta.huntsphere.dto.CompetitionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CompetitionService {
    CompetitionDTO createCompetition(CompetitionDTO competitionDTO);
    CompetitionDTO updateCompetition(UUID id, CompetitionDTO competitionDTO);
    void deleteCompetition(UUID id);
    CompetitionDTO getCompetitionById(UUID id);
    List<CompetitionDTO> getActiveCompetitions();
}
