package com.example.Gestion.Controller;

import com.example.Gestion.Models.LeaveBalance;
import com.example.Gestion.Service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-balances")
public class LeaveBalanceController {
 @Autowired
        private LeaveBalanceService leaveBalanceService;
        @PostMapping("create")
        public ResponseEntity<LeaveBalance> createLeaveBalance(@RequestBody LeaveBalance leaveBalance) {
            LeaveBalance createdBalance = leaveBalanceService.createLeaveBalance(leaveBalance);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBalance);
        }

        @GetMapping("/{id}")
        public ResponseEntity<LeaveBalance> getLeaveBalanceById(@PathVariable Long id) {
            LeaveBalance leaveBalance = leaveBalanceService.getLeaveBalanceById(id);
            return ResponseEntity.ok(leaveBalance);
        }

        @GetMapping
        public ResponseEntity<List<LeaveBalance>> getAllLeaveBalances() {
            List<LeaveBalance> leaveBalances = leaveBalanceService.getAllLeaveBalances();
            return ResponseEntity.ok(leaveBalances);
        }

        @PutMapping("/{id}")
        public ResponseEntity<LeaveBalance> updateLeaveBalance(@PathVariable Long id, @RequestBody LeaveBalance updatedLeaveBalance) {
            LeaveBalance updatedBalance = leaveBalanceService.updateLeaveBalance(id, updatedLeaveBalance);
            return ResponseEntity.ok(updatedBalance);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteLeaveBalance(@PathVariable Long id) {
            leaveBalanceService.deleteLeaveBalance(id);
            return ResponseEntity.noContent().build();
        }
    }

