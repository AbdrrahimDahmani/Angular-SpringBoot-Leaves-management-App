package com.example.Gestion.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Table(name = "LeaveType ")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int allocated_days;

    @OneToMany(mappedBy = "leaveType", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<LeaveRequest> leaveRequests ;

    @OneToMany(mappedBy = "leaveType", cascade = CascadeType.ALL)
    private List<LeaveBalance> leaveBalances ;

    @Override
    public String toString() {
        return "LeaveType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", allocated_days=" + allocated_days +
                ", leaveRequests=" + leaveRequests +
                ", leaveBalances=" + leaveBalances +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllocated_days(int allocated_days) {
        this.allocated_days = allocated_days;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public void setLeaveBalances(List<LeaveBalance> leaveBalances) {
        this.leaveBalances = leaveBalances;
    }
}
