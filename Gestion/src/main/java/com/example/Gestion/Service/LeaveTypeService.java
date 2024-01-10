package com.example.Gestion.Service;

import com.example.Gestion.Models.LeaveRequest;
import com.example.Gestion.Models.LeaveType;
import com.example.Gestion.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LeaveTypeService {
    LeaveRequest getLeaveRequestById(Long id);
     List<LeaveType> getAllLeaveType();
    LeaveType updateLeaveType( Long id, LeaveType updatedLeaveType);

    LeaveType createLeaveType(LeaveType leaveType);

    LeaveType getLeaveTypeById(Long id);
    ResponseEntity<Void> deleteLeaveType( Long id);
}
