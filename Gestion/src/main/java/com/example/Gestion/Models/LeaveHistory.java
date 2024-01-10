package com.example.Gestion.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "LeaveHistory ")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   /* @ManyToOne
    @JoinColumn(name = "leave_request_id")
    private LeaveRequest leaveRequest;
   */
    private LocalDateTime action_date;
    private String performed_action;
    private String comment;

    @Override
    public String toString() {
        return "LeaveHistory{" +
                "id=" + id +
               // ", leaveRequest=" + leaveRequest +
                ", action_date=" + action_date +
                ", performed_action='" + performed_action + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }
   */
   /* public void setLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }
   */
    public LocalDateTime getAction_date() {
        return action_date;
    }

    public void setAction_date(LocalDateTime action_date) {
        this.action_date = action_date;
    }

    public String getPerformed_action() {
        return performed_action;
    }

    public void setPerformed_action(String performed_action) {
        this.performed_action = performed_action;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
