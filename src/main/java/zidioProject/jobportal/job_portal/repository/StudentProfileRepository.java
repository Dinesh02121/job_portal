package zidioProject.jobportal.job_portal.repository;

import zidioProject.jobportal.job_portal.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {
}
