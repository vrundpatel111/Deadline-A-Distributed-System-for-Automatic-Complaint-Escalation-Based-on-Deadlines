package com.auracity.complaint_service.dto;

import com.auracity.complaint_service.entity.Severity;
import lombok.Data;

@Data
public class ComplaintRequest {
    private Long citizenId;
    private String title;
    private String description;
    private String location;
    private Severity severity; // Optional, default in Service/Entity logic
}
