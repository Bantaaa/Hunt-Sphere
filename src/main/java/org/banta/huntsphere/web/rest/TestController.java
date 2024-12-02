package org.banta.huntsphere.web.rest;

import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.service.security.AuthenticationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
@RequiredArgsConstructor
public class TestController {
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminOnly() {
        return "Admin access";
    }

    @PreAuthorize("hasAnyRole('ADMIN','JURY')")
    @GetMapping("/admin-or-jury")
    public String adminOrJury() {
        return "Admin or Jury access";
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/user")
    public String memberOrSelfAccess() {
        return "Member role or user themselves";
    }

    @GetMapping("/test")
    public void test(Authentication authentication) {
        System.out.println("Authorities: " + authentication.getAuthorities());
        System.out.println("Principal: " + authentication.getPrincipal());
    }
}