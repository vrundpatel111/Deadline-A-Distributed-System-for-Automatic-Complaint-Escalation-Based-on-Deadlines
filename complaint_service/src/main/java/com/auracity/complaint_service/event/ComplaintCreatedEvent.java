package com.auracity.complaint_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintCreatedEvent {
    private String complaintId;
    private String severity; // "HIGH", "MEDIUM", "LOW"
    private LocalDateTime createdAt;
}
