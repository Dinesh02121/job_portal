package zidioProject.jobportal.job_portal.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_listing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", referencedColumnName = "id", nullable = false)
    private RecruiterProfile recruiter;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(length = 50)
    private String location;

    @Enumerated(EnumType.STRING)
    private JobType type;

    public enum JobType {
        INTERNSHIP,
        JOB
    }
}
