package com.example.Gestion.Service.implementation;

import com.example.Gestion.Models.LeaveBalance;
import com.example.Gestion.Repository.LeaveBalanceRepository;
import com.example.Gestion.Service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeaveBalanceImplement implements LeaveBalanceService {
    @Autowired
        private LeaveBalanceRepository leaveBalanceRepository;

        @Override
        public LeaveBalance createLeaveBalance(LeaveBalance leaveBalance) {
            return leaveBalanceRepository.save(leaveBalance);
        }

        @Override
        public LeaveBalance getLeaveBalanceById(Long id) {
            return leaveBalanceRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveBalance not found"));
        }

        @Override
        public List<LeaveBalance> getAllLeaveBalances() {
            return leaveBalanceRepository.findAll();
        }

        @Override
        public LeaveBalance updateLeaveBalance(Long id, LeaveBalance updatedLeaveBalance) {
            LeaveBalance leaveBalance = getLeaveBalanceById(id);
            leaveBalance.setUser(updatedLeaveBalance.getUser());
            leaveBalance.setLeaveType(updatedLeaveBalance.getLeaveType());
            leaveBalance.setBalance(updatedLeaveBalance.getBalance());
            return leaveBalanceRepository.save(leaveBalance);
        }

        @Override
        public void deleteLeaveBalance(Long id) {
            LeaveBalance leaveBalance = getLeaveBalanceById(id);
            leaveBalanceRepository.delete(leaveBalance);
        }
    }

