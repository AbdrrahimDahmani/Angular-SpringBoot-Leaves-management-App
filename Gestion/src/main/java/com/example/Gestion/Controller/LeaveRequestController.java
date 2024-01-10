package com.example.Gestion.Controller;

import com.example.Gestion.Models.LeaveRequest;
import com.example.Gestion.Models.LeaveStatus;
import com.example.Gestion.Models.User;
import com.example.Gestion.Service.implementation.LeaveRequestImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestImplement leaveRequestService;

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<LeaveRequest>> getAllHistoryByUserId(@PathVariable User userId){
        List<LeaveRequest> requestHistory= this.leaveRequestService.getAllHistoryByUserId(userId);
        if(requestHistory!=null){
            return ResponseEntity.ok(requestHistory);
        }
        throw new NoSuchElementException("No History found");
    }
    @GetMapping("/hr/pending")
    public ResponseEntity<List<LeaveRequest>> getAllPendingRequests(){
        List<LeaveRequest> pendingRequests= this.leaveRequestService.getAllPendingRequests();
        if(pendingRequests!=null){
            return ResponseEntity.ok(pendingRequests);
        }
        throw new NoSuchElementException("No Hr Pending Request Found");
    }
    @GetMapping("/hr/requests")
    public ResponseEntity<List<LeaveRequest>> findAllRequests(){
        List<LeaveRequest> historyRequests= this.leaveRequestService.getAllRequests();
        if(historyRequests!=null){
            return ResponseEntity.ok(historyRequests);
        }
        throw new NoSuchElementException("No Employees History Requests Found");
    }
    @PostMapping("/create")
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        LeaveRequest createdRequest = leaveRequestService.createLeaveRequest(leaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

 /*   @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return ResponseEntity.ok(leaveRequest);
    }
*/


    @GetMapping("/{userId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByUserId(
            @PathVariable User userId

    ) {
        List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestsByUserId(userId);
        if(leaveRequests!=null){
            return ResponseEntity.ok(leaveRequests);
        }
        throw new NoSuchElementException("No request found");
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        return ResponseEntity.ok(leaveRequests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequest(@PathVariable Long id,
            @RequestBody LeaveRequest updatedLeaveRequest) {
        LeaveRequest updatedRequest = leaveRequestService.updateLeaveRequest(id, updatedLeaveRequest);
        return ResponseEntity.ok(updatedRequest);
    }
    @PatchMapping ("/hr/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequestStatus(@PathVariable Long id,
                                                                 @RequestParam LeaveStatus status) {
        LeaveRequest updatedRequestStatus = leaveRequestService.updateLeaveRequestStatus(id,status);
        return ResponseEntity.ok(updatedRequestStatus);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return ResponseEntity.noContent().build();
    }
}