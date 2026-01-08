package com.auracity.escalation_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "escalation_schedule")
public class EscalationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String complaintId;

    @Column(nullable = false)
    private LocalDateTime escalationDeadline;

    @Column(nullable = false)
    private String currentLevel; // e.g., "LEVEL_1", "LEVEL_2"

    @Column(nullable = false)
    private boolean resolved;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
