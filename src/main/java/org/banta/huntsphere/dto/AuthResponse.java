package org.banta.huntsphere.dto;

import lombok.Builder;
import lombok.Data;
import org.banta.huntsphere.domain.enums.Role;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String username;
    private Role role;
}