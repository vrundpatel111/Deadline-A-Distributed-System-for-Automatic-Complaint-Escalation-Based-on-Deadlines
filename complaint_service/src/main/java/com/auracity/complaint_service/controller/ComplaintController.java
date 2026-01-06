package com.auracity.complaint_service.controller;

import com.auracity.complaint_service.dto.ComplaintRequest;
import com.auracity.complaint_service.entity.Complaint;
import com.auracity.complaint_service.entity.ComplaintStatus;
import com.auracity.complaint_service.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody ComplaintRequest request) {
        Complaint complaint = new Complaint();
        complaint.setCitizenId(request.getCitizenId());
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setLocation(request.getLocation());
        if (request.getSeverity() != null) {
            complaint.setSeverity(request.getSeverity());
        }

        Complaint created = complaintService.createComplaint(complaint);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.getComplaintById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Complaint> updateStatus(@PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        String statusStr = statusUpdate.get("status");
        if (statusStr == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            ComplaintStatus status = ComplaintStatus.valueOf(statusStr.toUpperCase());
            boolean updated = complaintService.updateStatus(id, status);
            if (updated) {
                return ResponseEntity.ok(complaintService.getComplaintById(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // Or 409 Conflict
        }
    }
}
