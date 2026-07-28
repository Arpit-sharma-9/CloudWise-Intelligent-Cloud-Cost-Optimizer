package com.cloudwise.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
@Data
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resource_id", nullable = false)
    private Long resourceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommendation_type", nullable = false)
    private RecommendationType recommendationType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "estimated_savings", precision = 10, scale = 2)
    private BigDecimal estimatedSavings;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RecommendationStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum RecommendationType {
        RIGHTSIZE, STOP, DELETE, MOVE_STORAGE, DOWN_SIZE
    }

    public enum RecommendationStatus {
        PENDING, APPROVED, REJECTED, EXECUTED
    }
}
