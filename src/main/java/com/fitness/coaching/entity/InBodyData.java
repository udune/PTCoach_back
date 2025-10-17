package com.fitness.coaching.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inbody_data")
@Data
public class InBodyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, precision = 5, scale = 2) // e.g., 99.99
    private BigDecimal weight;

    @Column(name = "bodyFatPercentage", nullable = false, precision = 5, scale = 2) // e.g., 99.99
    private BigDecimal bodyFat;

    @Column(name = "muscle_mass", nullable = false, precision = 5, scale = 2) // e.g., 99.99
    private BigDecimal muscleMass;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal bmi; // e.g., 99.99

    @Column(name = "measured_at", nullable = false)
    private LocalDateTime measuredAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
