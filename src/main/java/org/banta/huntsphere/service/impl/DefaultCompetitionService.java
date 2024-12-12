package org.banta.huntsphere.service.impl;

import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.dto.CompetitionDTO;
import org.banta.huntsphere.repository.CompetitionRepository;
import org.banta.huntsphere.service.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultCompetitionService implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Override
    public CompetitionDTO createCompetition(CompetitionDTO competitionDTO) {
        return null;
    }

    @Override
    public CompetitionDTO updateCompetition(UUID id, CompetitionDTO competitionDTO) {
        return null;
    }

    @Override
    public void deleteCompetition(UUID id) {
    }

    @Override
    public CompetitionDTO getCompetitionById(UUID id) {
        return null;
    }

    @Override
    public List<CompetitionDTO> getActiveCompetitions() {
        // Just return empty list - simplest possible implementation
        return new ArrayList<>();
    }
}