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

    @Column(name = "recommendation_type", nullable = false)
    private String recommendationType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "estimated_savings", precision = 10, scale = 2)
    private BigDecimal estimatedSavings;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
