package org.banta.huntsphere.service.security;

import lombok.RequiredArgsConstructor;
import org.banta.huntsphere.domain.entity.AppUser;
import org.banta.huntsphere.domain.enums.Role;
import org.banta.huntsphere.repository.UserRepository;
import org.banta.security.dto.RegisterRequest;
import org.banta.security.model.BaseUserDetails;
import org.banta.security.service.UserRegistrationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserRegistrationService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public BaseUserDetails createUser(RegisterRequest request) {
        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setPassword(request.getPassword()); // Already encoded by RegistrationService
        appUser.setRole(Role.MEMBER);
        appUser.setEmail(request.getEmail());
        appUser.setFirstName((String) request.getAdditionalFields().get("firstName"));
        appUser.setLastName((String) request.getAdditionalFields().get("lastName"));
        appUser.setCin((String) request.getAdditionalFields().get("cin"));
        appUser.setNationality((String) request.getAdditionalFields().get("nationality"));
        appUser.setLicenseExpirationDate((LocalDateTime) request.getAdditionalFields().get("licenseExpirationDate"));
        appUser.setIsActive(true);
        appUser.setJoinDate(LocalDateTime.now());
        appUser.setLastLogin(null);

        return userRepository.save(appUser);
    }

//    @Override
//    public BaseUserDetails createUser(RegisterRequest request) {
//        try {
//            System.out.println("Starting user creation...");
//            System.out.println("Username: " + request.getUsername());
//            System.out.println("Email: " + request.getEmail());
//            System.out.println("Additional fields: " + request.getAdditionalFields());
//
//            AppUser appUser = new AppUser();
//            appUser.setUsername(request.getUsername());
//            appUser.setPassword(request.getPassword());
//            appUser.setRole(Role.MEMBER);
//            appUser.setEmail(request.getEmail());
//
//            try {
//                appUser.setFirstName((String) request.getAdditionalFields().get("firstName"));
//                System.out.println("First name set: " + appUser.getFirstName());
//            } catch (Exception e) {
//                System.out.println("Error setting firstName: " + e.getMessage());
//            }
//
//            try {
//                appUser.setLastName((String) request.getAdditionalFields().get("lastName"));
//                System.out.println("Last name set: " + appUser.getLastName());
//            } catch (Exception e) {
//                System.out.println("Error setting lastName: " + e.getMessage());
//            }
//
//            try {
//                appUser.setCin((String) request.getAdditionalFields().get("cin"));
//                System.out.println("CIN set: " + appUser.getCin());
//            } catch (Exception e) {
//                System.out.println("Error setting cin: " + e.getMessage());
//            }
//
//            try {
//                appUser.setNationality((String) request.getAdditionalFields().get("nationality"));
//                System.out.println("Nationality set: " + appUser.getNationality());
//            } catch (Exception e) {
//                System.out.println("Error setting nationality: " + e.getMessage());
//            }
//
//            try {
//                String dateStr = (String) request.getAdditionalFields().get("licenseExpirationDate");
//                LocalDateTime date = LocalDateTime.parse(dateStr);
//                appUser.setLicenseExpirationDate(date);
//                System.out.println("License date set: " + appUser.getLicenseExpirationDate());
//            } catch (Exception e) {
//                System.out.println("Error setting licenseExpirationDate: " + e.getMessage());
//            }
//
//            appUser.setIsActive(true);
//            appUser.setJoinDate(LocalDateTime.now());
//            appUser.setLastLogin(null);
//
//            System.out.println("Attempting to save user...");
//            return userRepository.save(appUser);
//
//        } catch (Exception e) {
//            System.out.println("Error in createUser: " + e.getMessage());
//            e.printStackTrace();
//            throw e;
//        }
//    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}