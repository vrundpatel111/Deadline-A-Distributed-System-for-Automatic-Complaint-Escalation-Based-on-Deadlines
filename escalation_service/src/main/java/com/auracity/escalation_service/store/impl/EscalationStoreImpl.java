package com.auracity.escalation_service.store.impl;

import com.auracity.escalation_service.entity.EscalationSchedule;
import com.auracity.escalation_service.store.EscalationStore;
import com.auracity.escalation_service.store.jpa.EscalationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EscalationStoreImpl implements EscalationStore {

    private final EscalationJpaRepository jpaRepository;

    @Override
    public EscalationSchedule save(EscalationSchedule schedule) {
        return jpaRepository.save(schedule);
    }

    @Override
    public Optional<EscalationSchedule> findByComplaintId(String complaintId) {
        return Optional.ofNullable(jpaRepository.findByComplaintId(complaintId));
    }

    @Override
    public List<EscalationSchedule> findPendingEscalations(LocalDateTime now) {
        return jpaRepository.findByResolvedFalseAndEscalationDeadlineBefore(now);
    }
}
