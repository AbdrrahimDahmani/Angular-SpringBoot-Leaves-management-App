package com.example.Gestion.Service;

import com.example.Gestion.Models.LeaveRequest;
import com.example.Gestion.Models.LeaveStatus;
import com.example.Gestion.Models.User;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequest getLeaveRequestById(Long id);

    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest);

    LeaveRequest updateLeaveRequest(Long id, LeaveRequest updatedLeaveRequest);

    LeaveRequest updateLeaveRequestStatus(Long id, LeaveStatus status);

    List<LeaveRequest> getAllLeaveRequests();


    List<LeaveRequest> getLeaveRequestsByUserId(User id);
    List<LeaveRequest> getAllHistoryByUserId(User id);
    List<LeaveRequest> getAllPendingRequests();
    List<LeaveRequest> getAllRequests();
    void deleteLeaveRequest(Long id);
}
