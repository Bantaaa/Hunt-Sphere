package org.banta.huntsphere.repository;

import org.banta.huntsphere.domain.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {
    boolean existsByName(String name);
}