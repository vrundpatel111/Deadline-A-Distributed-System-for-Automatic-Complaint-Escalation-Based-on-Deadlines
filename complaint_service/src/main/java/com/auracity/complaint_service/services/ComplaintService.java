package com.auracity.complaint_service.services;

import com.auracity.complaint_service.dao.ComplaintDAO;
import com.auracity.complaint_service.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintDAO complaintDAO;

    @Autowired
    public ComplaintService(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    public Complaint createComplaint(Complaint complaint) {
        return complaintDAO.save(complaint);
    }

    public Complaint getComplaintById(Long id) {
        return complaintDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    public boolean updateStatus(Long id, ComplaintStatus status) {
        Optional<Complaint> complaintOptional = complaintDAO.findById(id);

        if (complaintOptional.isEmpty()) {
            return false;
        }

        Complaint complaint = complaintOptional.get();

        if (complaint.getStatus() == ComplaintStatus.RESOLVED) {
            throw new IllegalStateException("Resolved complaint cannot be updated");
        }

        complaint.setStatus(status);
        complaintDAO.save(complaint);
        return true;
    }

    public Complaint escalateComplaint(Long id) {
        Complaint complaint = complaintDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setCurrentLevel(complaint.getCurrentLevel() + 1);
        complaint.setStatus(ComplaintStatus.ESCALATED);

        return complaintDAO.save(complaint);
    }

}
