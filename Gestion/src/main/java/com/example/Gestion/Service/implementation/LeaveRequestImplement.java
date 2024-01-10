package com.example.Gestion.Service.implementation;

import com.example.Gestion.Models.LeaveRequest;
import com.example.Gestion.Models.LeaveStatus;
import com.example.Gestion.Models.User;
import com.example.Gestion.Repository.LeaveRequestRepository;
import com.example.Gestion.Service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeaveRequestImplement implements LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequestDto) {
        LeaveRequest leaveRequest = new LeaveRequest();
        System.out.println(leaveRequestDto.getLeaveType());
        leaveRequest.setStart_date(leaveRequestDto.getStart_date());
        leaveRequest.setEnd_date(leaveRequestDto.getEnd_date());
        leaveRequest.setLeaveType(leaveRequestDto.getLeaveType());
        leaveRequest.setUser(leaveRequestDto.getUser());
        leaveRequest.setComment(leaveRequestDto.getComment());
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveRequest not found"));
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {

        return leaveRequestRepository.findAll();
    }

    @Override
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest updatedLeaveRequest) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        leaveRequest.setUser(updatedLeaveRequest.getUser());
        leaveRequest.setLeaveType(updatedLeaveRequest.getLeaveType());
        leaveRequest.setStart_date(updatedLeaveRequest.getStart_date());
        leaveRequest.setEnd_date(updatedLeaveRequest.getEnd_date());
        leaveRequest.setStatus(updatedLeaveRequest.getStatus());
        leaveRequest.setComment(updatedLeaveRequest.getComment());
        return leaveRequestRepository.save(leaveRequest);
    }

        @Override
        public LeaveRequest updateLeaveRequestStatus(Long id, LeaveStatus status) {
            LeaveRequest leaveRequest=getLeaveRequestById(id);
            leaveRequest.setStatus(status);
            return leaveRequestRepository.save(leaveRequest);
        }

    @Override
    public void deleteLeaveRequest(Long id) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        leaveRequestRepository.delete(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByUserId(User userId) {
        {
            return leaveRequestRepository.findAllByUserId(userId,LeaveStatus.Pending);
        }

    }

    @Override
    public List<LeaveRequest> getAllHistoryByUserId(User id) {
        return leaveRequestRepository.findAllHistoryByUserId(id, LeaveStatus.Approved,LeaveStatus.Declined);
    }

    @Override
    public List<LeaveRequest> getAllPendingRequests() {
        return leaveRequestRepository.findAllPendingRequests(LeaveStatus.Pending);
    }

    @Override
    public List<LeaveRequest> getAllRequests() {
        return leaveRequestRepository.findAllRequests(LeaveStatus.Pending);
    }
}
