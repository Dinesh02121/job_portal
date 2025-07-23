package zidioProject.jobportal.job_portal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zidioProject.jobportal.job_portal.dto.LoginRequest;
import zidioProject.jobportal.job_portal.dto.LoginResponse;
import zidioProject.jobportal.job_portal.dto.RegistrationRequest;
import zidioProject.jobportal.job_portal.entity.*;
import zidioProject.jobportal.job_portal.repository.*;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // ✅ Manual constructor replaces Lombok's @RequiredArgsConstructor
    public AuthService(UserRepository userRepository,
                       StudentProfileRepository studentProfileRepository,
                       RecruiterProfileRepository recruiterProfileRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void registerUser(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User.Role role = User.Role.valueOf(request.getRole().toUpperCase());
        User user = new User();
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        User savedUser = userRepository.save(user);

        if (role == User.Role.STUDENT) {
            StudentProfile profile = new StudentProfile();
            profile.setUser(savedUser);
            profile.setResumeUrl(request.getResumeUrl());
            profile.setEducation(request.getEducation());
            profile.setSkills(request.getSkills());
            studentProfileRepository.save(profile);
        } else if (role == User.Role.RECRUITER) {
            RecruiterProfile profile = new RecruiterProfile();
            profile.setUser(savedUser);
            profile.setCompanyName(request.getCompanyName());
            profile.setDesignation(request.getDesignation());
            recruiterProfileRepository.save(profile);
        }
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponse(token);
    }

    public ResponseEntity<?> register(RegistrationRequest request) {
        registerUser(request);
        return ResponseEntity.ok("Registration successful!");
    }
}
