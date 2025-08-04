package Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "CheckHistory")
@Data
public class CheckHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Instant timestamp;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "response_time_ms")
    private Integer responseTimeMs;

    @Column(name = "success")
    private boolean success;

    @Column(name = "details", columnDefinition = "json")
    private String details;

    @Column(name = "threshold_violated")
    private boolean thresholdViolated;

    @Column(name = "performance_rating")
    private Integer performanceRating;

    @Column(name = "endpoint_id")
    private Integer endpointId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endpoint_id", nullable = false)
    private Endpoint endpoint;















}//CheckHistory
