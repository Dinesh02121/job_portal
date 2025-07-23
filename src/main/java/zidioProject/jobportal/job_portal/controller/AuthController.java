package zidioProject.jobportal.job_portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zidioProject.jobportal.job_portal.dto.LoginResponse;
import zidioProject.jobportal.job_portal.dto.RegistrationRequest;
import zidioProject.jobportal.job_portal.dto.LoginRequest;
import zidioProject.jobportal.job_portal.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
