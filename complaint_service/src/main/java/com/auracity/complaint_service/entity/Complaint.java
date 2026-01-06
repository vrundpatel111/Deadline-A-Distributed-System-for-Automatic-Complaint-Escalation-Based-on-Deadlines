package com.auracity.complaint_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who raised the complaint
    @Column(nullable = false)
    private Long citizenId; // Or UUID if Citizen is also UUID

    // what kind of issue
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    // where the issue occurred
    @Column(nullable = true)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus status;

    // escalation level (1 = local, 2 = district, 3 = state)
    @Column(nullable = false)
    private Integer currentLevel = 1; // Default to 1

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        lastUpdatedAt = LocalDateTime.now();
        if (currentLevel == null)
            currentLevel = 1;
        if (status == null)
            status = ComplaintStatus.OPEN;
        if (severity == null)
            severity = Severity.LOW;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = LocalDateTime.now();
    }
}
