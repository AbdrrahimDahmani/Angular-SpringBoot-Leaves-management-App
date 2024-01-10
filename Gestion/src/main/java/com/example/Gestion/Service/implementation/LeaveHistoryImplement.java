package com.example.Gestion.Service.implementation;

import com.example.Gestion.Models.LeaveHistory;
import com.example.Gestion.Repository.LeaveHistoryRepository;
import com.example.Gestion.Service.LeaveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeaveHistoryImplement  implements LeaveHistoryService {

        @Autowired
        private LeaveHistoryRepository leaveHistoryRepository;

        @Override
        public LeaveHistory createLeaveHistory(LeaveHistory leaveHistory) {
            return leaveHistoryRepository.save(leaveHistory);
        }

        @Override
        public LeaveHistory getLeaveHistoryById(Long id) {
            return leaveHistoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveHistory not found"));
        }

        @Override
        public List<LeaveHistory> getAllLeaveHistories() {
            return leaveHistoryRepository.findAll();
        }

        @Override
        public LeaveHistory updateLeaveHistory(Long id, LeaveHistory updatedLeaveHistory) {
            LeaveHistory leaveHistory = getLeaveHistoryById(id);
           // leaveHistory.setLeaveRequest(updatedLeaveHistory.getLeaveRequest());
            leaveHistory.setAction_date(updatedLeaveHistory.getAction_date());
            leaveHistory.setPerformed_action(updatedLeaveHistory.getPerformed_action());
            leaveHistory.setComment(updatedLeaveHistory.getComment());
            return leaveHistoryRepository.save(leaveHistory);
        }

        @Override
        public void deleteLeaveHistory(Long id) {
            LeaveHistory leaveHistory = getLeaveHistoryById(id);
            leaveHistoryRepository.delete(leaveHistory);
        }
    }

