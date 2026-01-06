package com.auracity.complaint_service.dao;

import com.auracity.complaint_service.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComplaintDAO extends JpaRepository<Complaint, Long> {
}
