package com.example.Gestion.Service;

import com.example.Gestion.Models.LeaveBalance;

import java.util.List;

public interface LeaveBalanceService {

       LeaveBalance createLeaveBalance(LeaveBalance leaveBalance);

        LeaveBalance getLeaveBalanceById(Long id);

        List<LeaveBalance> getAllLeaveBalances();

        LeaveBalance updateLeaveBalance(Long id, LeaveBalance updatedLeaveBalance);

        void deleteLeaveBalance(Long id);
    }


