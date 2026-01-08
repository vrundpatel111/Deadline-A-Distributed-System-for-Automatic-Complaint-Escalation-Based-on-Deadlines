package com.auracity.escalation_service.store;

import com.auracity.escalation_service.entity.EscalationSchedule;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EscalationStore {
    EscalationSchedule save(EscalationSchedule schedule);

    Optional<EscalationSchedule> findByComplaintId(String complaintId);

    List<EscalationSchedule> findPendingEscalations(LocalDateTime now);
}
