package org.banta.huntsphere.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.banta.huntsphere.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null.")
    private Role role;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String cin;

    @Column(unique = true)
    private String email;

    private String nationality;
    private LocalDateTime joinDate;
    private LocalDateTime licenseExpirationDate;
    private LocalDateTime lastLogin;
    private Boolean isActive;
}