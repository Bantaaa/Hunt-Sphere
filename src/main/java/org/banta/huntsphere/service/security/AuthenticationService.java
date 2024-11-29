package org.banta.huntsphere.service.security;

import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.domain.entity.AppUser;
import org.banta.huntsphere.domain.enums.Role;
import org.banta.huntsphere.dto.AuthRequest;
import org.banta.huntsphere.dto.RegisterRequest;
import org.banta.huntsphere.dto.AuthResponse;
import org.banta.huntsphere.repository.UserRepository;
import org.banta.huntsphere.web.error.exception.resource.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordValidator passwordValidator;


    public AuthResponse register(RegisterRequest request) {
        if (passwordValidator.isInvalid(request.getPassword())) {
            throw new BadRequestException(passwordValidator.getPasswordRequirements());
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setRole(Role.MEMBER);
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setCin(request.getCin());
        appUser.setEmail(request.getEmail());
        appUser.setNationality(request.getNationality());
        appUser.setJoinDate(LocalDateTime.now());
        appUser.setLicenseExpirationDate(request.getLicenseExpirationDate());
        appUser.setIsActive(true);
        appUser.setLastLogin(null);

        AppUser savedAppUser = userRepository.save(appUser);
        String token = jwtService.generateToken(savedAppUser);

        return AuthResponse.builder()
                .token(token)
                .username(savedAppUser.getUsername())
                .role(savedAppUser.getRole())
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        AppUser appUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        String token = jwtService.generateToken(appUser);

        return AuthResponse.builder()
                .token(token)
                .username(appUser.getUsername())
                .role(appUser.getRole())
                .build();
    }
    // password validation during user creation/password change
    public void validatePassword(String password) {
        if (passwordValidator.isInvalid(password)) {
            throw new BadRequestException(passwordValidator.getPasswordRequirements());
        }
    }
}