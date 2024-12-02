package org.banta.huntsphere.web.rest;

import lombok.RequiredArgsConstructor;
import org.banta.security.annotation.HasRole;
import org.banta.huntsphere.service.security.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final AuthenticationService authenticationService;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public access successful!";
    }

    @HasRole("MEMBER")
    @GetMapping("/secured")
    public String securedEndpoint() {
        return "Secured access successful!";
    }
}