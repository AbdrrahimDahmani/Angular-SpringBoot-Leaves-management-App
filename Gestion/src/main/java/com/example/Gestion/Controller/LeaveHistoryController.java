package com.example.Gestion.Controller;
import com.example.Gestion.Models.LeaveHistory;
import com.example.Gestion.Service.LeaveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leave-history")

public class LeaveHistoryController {

        private final LeaveHistoryService leaveHistoryService;

        @Autowired
        public LeaveHistoryController(LeaveHistoryService leaveHistoryService) {
            this.leaveHistoryService = leaveHistoryService;
        }

        @GetMapping
        public ResponseEntity<List<LeaveHistory>> getAllLeaveHistory() {
            List<LeaveHistory> leaveHistoryList = leaveHistoryService.getAllLeaveHistories();
            return ResponseEntity.ok(leaveHistoryList);
        }

        @GetMapping("/{id}")
        public ResponseEntity<LeaveHistory> getLeaveHistoryById(@PathVariable Long id) {
            LeaveHistory leaveHistory = leaveHistoryService.getLeaveHistoryById(id);
            return ResponseEntity.ok(leaveHistory);
        }

        @PostMapping("create")
        public ResponseEntity<LeaveHistory> createLeaveHistory(@RequestBody LeaveHistory leaveHistory) {
            LeaveHistory createdLeaveHistory = leaveHistoryService.createLeaveHistory(leaveHistory);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLeaveHistory);
        }

        @PutMapping("/{id}")
        public ResponseEntity<LeaveHistory> updateLeaveHistory(@PathVariable Long id, @RequestBody LeaveHistory leaveHistory) {
            LeaveHistory updatedLeaveHistory = leaveHistoryService.updateLeaveHistory(id, leaveHistory);
            return ResponseEntity.ok(updatedLeaveHistory);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteLeaveHistory(@PathVariable Long id) {
            leaveHistoryService.deleteLeaveHistory(id);
            return ResponseEntity.noContent().build();
        }
    }


