package zidioProject.jobportal.job_portal.repository;

import zidioProject.jobportal.job_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
