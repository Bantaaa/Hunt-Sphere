package org.banta.huntsphere.repository;

import org.banta.huntsphere.domain.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
    boolean existsByCode(String code);
}
