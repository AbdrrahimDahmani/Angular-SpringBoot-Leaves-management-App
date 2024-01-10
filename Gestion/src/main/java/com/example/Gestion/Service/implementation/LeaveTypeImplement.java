package com.example.Gestion.Service.implementation;
import com.example.Gestion.Models.LeaveRequest;
import com.example.Gestion.Models.LeaveType;
import com.example.Gestion.Repository.LeaveTypeRepository;
import com.example.Gestion.Service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeaveTypeImplement  implements LeaveTypeService {


    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    @Override
    public LeaveType createLeaveType(LeaveType leaveType) {
        return leaveTypeRepository.save(leaveType);
    }
    @Override
    public LeaveType getLeaveTypeById(Long id) {
        return leaveTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveRequest not found"));
    }

    @Override
    public LeaveRequest getLeaveRequestById(Long id) {
        return null;
    }

    @Override
    public List<LeaveType> getAllLeaveType() {
        return leaveTypeRepository.findAll();
    }
    @Override
    public LeaveType updateLeaveType(@PathVariable Long id, @RequestBody LeaveType updatedLeaveType) {
        LeaveType leaveType = getLeaveTypeById(id);
        leaveType .setName(updatedLeaveType.getName());
        leaveType .setAllocated_days(updatedLeaveType.getAllocated_days());
        leaveType .setLeaveRequests(updatedLeaveType.getLeaveRequests());
        leaveType .setLeaveBalances(updatedLeaveType.getLeaveBalances());
        return leaveTypeRepository.save(leaveType);
    }
    @Override
    public ResponseEntity<Void> deleteLeaveType(@PathVariable Long id) {
        LeaveType leaveType = leaveTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveType not found"));
        leaveTypeRepository.delete(leaveType);
        return org.springframework.http.ResponseEntity.noContent().build();
    }
}

