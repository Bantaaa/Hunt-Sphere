package org.banta.huntsphere.web.rest;

import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.domain.entity.AppUser;
import org.banta.huntsphere.domain.enums.Role;
import org.banta.huntsphere.dto.AppUserDto;
import org.banta.huntsphere.dto.KeycloakLoginRequest;
import org.banta.huntsphere.dto.TokenResponse;
import org.banta.huntsphere.repository.UserRepository;
import org.banta.huntsphere.service.security.KeycloakAuthService;
import org.banta.huntsphere.web.error.exception.resource.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final KeycloakAuthService keycloakAuthService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AppUserDto userDto) {
        // Register in Keycloak first
        keycloakAuthService.registerUser(userDto);

        // Create in our database
        AppUser user = new AppUser();
        user.setUsername(userDto.getUsername());
        user.setPassword("KEYCLOAK_MANAGED");
        user.setRole(Role.MEMBER);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCin(userDto.getCin());
        user.setNationality(userDto.getNationality());
        user.setIsActive(true);
        user.setJoinDate(LocalDateTime.now());
        user.setLicenseExpirationDate(userDto.getLicenseExpirationDate());

        userRepository.save(user);

        // Return tokens
        TokenResponse token = keycloakAuthService.authenticate(userDto.getUsername(), userDto.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid KeycloakLoginRequest request) {
        // Verify user exists in our database
        if (!userRepository.existsByUsername(request.getUsername())) {
            throw new ResourceNotFoundException("User not found. Please register first.");
        }

        // Get token from Keycloak
        TokenResponse token = keycloakAuthService.authenticate(request.getUsername(), request.getPassword());

        // Update last login time
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok(token);
    }
}