package org.banta.huntsphere.dto;


import lombok.Data;
import org.banta.huntsphere.domain.enums.Role;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Password must be at least 8 characters and contain at least one number, one uppercase and one lowercase letter")
    private String password;

    @NotNull
    private Role role;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String cin;

    @Email
    @NotBlank
    private String email;

    private String nationality;
    private LocalDateTime joinDate;

    @Future
    private LocalDateTime licenseExpirationDate;
}
