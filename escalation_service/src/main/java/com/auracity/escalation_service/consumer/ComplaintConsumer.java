package com.auracity.escalation_service.consumer;

import com.auracity.escalation_service.event.ComplaintCreatedEvent;
import com.auracity.escalation_service.entity.EscalationSchedule;
import com.auracity.escalation_service.store.EscalationStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComplaintConsumer {

    private final EscalationStore escalationStore;

    @KafkaListener(topics = "complaint-topic", groupId = "escalation-group")
    public void consume(ComplaintCreatedEvent event) {
        log.info("Received ComplaintCreatedEvent: {}", event);

        LocalDateTime deadline = calculateDeadline(event.getSeverity(), event.getCreatedAt());

        EscalationSchedule schedule = EscalationSchedule.builder()
                .complaintId(event.getComplaintId())
                .escalationDeadline(deadline)
                .currentLevel("LEVEL_1")
                .resolved(false)
                .build();

        escalationStore.save(schedule);
        log.info("Saved EscalationSchedule for complaint: {}", event.getComplaintId());
    }

    private LocalDateTime calculateDeadline(String severity, LocalDateTime createdAt) {
        if (createdAt == null) createdAt = LocalDateTime.now();
        
        return switch (severity.toUpperCase()) {
            case "HIGH" -> createdAt.plusHours(2);
            case "MEDIUM" -> createdAt.plusDays(1);
            case "LOW" -> createdAt.plusDays(3);
            default -> createdAt.plusDays(2);
        };
    }
}
