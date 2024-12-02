package org.banta.huntsphere.repository;

import org.banta.huntsphere.domain.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCin(String cin);
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
}
