package com.example.Gestion.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Data
@Table(name = "LeaveRequest ")
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    //@JsonManagedReference
    private User user;
    @ManyToOne
    @JoinColumn(name = "leave_type_id")
   // @JsonManagedReference
    private LeaveType leaveType;

    private LocalDate start_date;
    private LocalDate end_date;
    @Column(name = "status", nullable = false)
        private LeaveStatus status;
    // private String status;
    private String comment;

/*    @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LeaveHistory> leaveHistory ;
*/
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = LeaveStatus.Pending;
        }
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "id=" + id +
                ", user=" + user +
                ", leaveType=" + leaveType +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
               // ", leaveHistory=" + leaveHistory +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   /* public void setLeaveHistory(List<LeaveHistory> leaveHistory) {
        this.leaveHistory = leaveHistory;
    }
   */
}

