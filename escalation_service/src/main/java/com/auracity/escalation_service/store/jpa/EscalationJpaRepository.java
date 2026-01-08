package com.auracity.escalation_service.store.jpa;

import com.auracity.escalation_service.entity.EscalationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EscalationJpaRepository extends JpaRepository<EscalationSchedule, Long> {
    EscalationSchedule findByComplaintId(String complaintId);

    List<EscalationSchedule> findByResolvedFalseAndEscalationDeadlineBefore(LocalDateTime now);
}
