package com.auracity.escalation_service.controller;

import com.auracity.escalation_service.entity.EscalationSchedule;
import com.auracity.escalation_service.store.jpa.EscalationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escalations")
@RequiredArgsConstructor
public class EscalationController {

    private final EscalationJpaRepository escalationRepository;

    /**
     * Get all escalation schedules
     */
    @GetMapping
    public ResponseEntity<List<EscalationSchedule>> getAllEscalations() {
        List<EscalationSchedule> escalations = escalationRepository.findAll();
        return ResponseEntity.ok(escalations);
    }

    /**
     * Get escalation by complaint ID
     */
    @GetMapping("/complaint/{complaintId}")
    public ResponseEntity<EscalationSchedule> getByComplaintId(@PathVariable String complaintId) {
        EscalationSchedule schedule = escalationRepository.findByComplaintId(complaintId);
        if (schedule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schedule);
    }

    /**
     * Get count of all escalations (for verification)
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        long count = escalationRepository.count();
        return ResponseEntity.ok(count);
    }
}
