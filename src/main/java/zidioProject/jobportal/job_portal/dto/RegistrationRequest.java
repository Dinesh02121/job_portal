package zidioProject.jobportal.job_portal.dto;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String name;
    private String email;      // ✅ Ye field zaruri hai
    private String password;
    private String role;       // e.g. STUDENT, RECRUITER, ADMIN

    // Student-specific fields
    private String resumeUrl;
    private String education;
    private String skills;

    // Recruiter-specific fields
    private String companyName;
    private String designation;
}
