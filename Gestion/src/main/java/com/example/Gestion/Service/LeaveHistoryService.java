package com.example.Gestion.Service;

import com.example.Gestion.Models.LeaveHistory;

import java.util.List;

public interface LeaveHistoryService {

        LeaveHistory createLeaveHistory(LeaveHistory leaveHistory);

        LeaveHistory getLeaveHistoryById(Long id);

        List<LeaveHistory> getAllLeaveHistories();


        LeaveHistory updateLeaveHistory(Long id, LeaveHistory updatedLeaveHistory);

        void deleteLeaveHistory(Long id);

    }


