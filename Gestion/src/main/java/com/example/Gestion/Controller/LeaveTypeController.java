package com.example.Gestion.Controller;


import com.example.Gestion.Models.LeaveType;
import com.example.Gestion.Models.User;
import com.example.Gestion.Repository.LeaveTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api/leave-types")
public class LeaveTypeController {
    @Autowired
    private LeaveTypeRepository leavetyperepository;

    @GetMapping
    public List<LeaveType> getAllLeaveTypes() {
        return leavetyperepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveType> getLeaveTypeById(@PathVariable Long id) {
        LeaveType leaveType = leavetyperepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveType not found"));
        return ResponseEntity.ok(leaveType);
    }

    @PostMapping("create")
    public LeaveType createLeaveType(@RequestBody LeaveType leaveType) {
        return leavetyperepository.save(leaveType);
    }

    @PutMapping("/{id}")
    public LeaveType updateLeaveType(@PathVariable Long id, @RequestBody LeaveType updatedLeaveType) {
        LeaveType leaveType = leavetyperepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveType not found"));

        leaveType.setName(updatedLeaveType.getName());
        leaveType.setAllocated_days(updatedLeaveType.getAllocated_days());


        return leavetyperepository.save(leaveType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveType(@PathVariable Long id) {
        LeaveType leaveType = leavetyperepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LeaveType not found"));

        leavetyperepository.delete(leaveType);
        return ResponseEntity.noContent().build();
    }
}
