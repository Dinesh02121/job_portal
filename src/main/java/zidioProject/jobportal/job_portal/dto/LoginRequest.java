package zidioProject.jobportal.job_portal.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
