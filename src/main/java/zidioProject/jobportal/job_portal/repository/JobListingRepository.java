package zidioProject.jobportal.job_portal.repository;

import zidioProject.jobportal.job_portal.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepository extends JpaRepository<JobListing, Integer> {
}
