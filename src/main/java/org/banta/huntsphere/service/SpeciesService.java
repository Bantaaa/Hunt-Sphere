package org.banta.huntsphere.service;

import org.banta.huntsphere.domain.enums.SpeciesType;
import org.banta.huntsphere.dto.SpeciesDTO;

import java.util.List;
import java.util.UUID;

public interface SpeciesService {
    SpeciesDTO createSpecies(SpeciesDTO speciesDTO);
    SpeciesDTO updateSpecies(UUID id, SpeciesDTO speciesDTO);
    void deleteSpecies(UUID id);
    SpeciesDTO getSpeciesById(UUID id);
    List<SpeciesDTO> getAllSpeciesByType(SpeciesType type);
}