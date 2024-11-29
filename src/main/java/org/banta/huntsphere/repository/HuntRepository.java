package org.banta.huntsphere.repository;

import org.banta.huntsphere.domain.entity.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HuntRepository extends JpaRepository<Hunt, UUID> {}
