package org.banta.huntsphere.service.security;

import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;

    public boolean isCurrentUser(UUID userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        String currentUsername = authentication.getName();
        return userRepository.findByUsername(currentUsername)
                .map(user -> user.getId().equals(userId))
                .orElse(false);
    }
}