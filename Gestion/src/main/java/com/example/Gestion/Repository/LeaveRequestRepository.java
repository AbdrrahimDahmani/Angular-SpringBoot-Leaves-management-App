package com.example.Gestion.Repository;

import com.example.Gestion.Models.LeaveRequest;
import com.example.Gestion.Models.LeaveStatus;
import com.example.Gestion.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    @Query("SELECT l FROM LeaveRequest l where l.user= ?1 and l.status=?2")
    List<LeaveRequest> findAllByUserId(User userId,LeaveStatus pending);

    @Query("SELECT l FROM LeaveRequest l where (l.user = ?1 and (l.status in (?2,?3) ) )")
    List<LeaveRequest> findAllHistoryByUserId(User userId, LeaveStatus approved,LeaveStatus declined);

    @Query("SELECT l,lt FROM LeaveRequest l,LeaveType lt where l.status=?1")
    List<LeaveRequest> findAllPendingRequests(LeaveStatus pending);

    @Query("SELECT l FROM LeaveRequest l where l.status !=?1")
    List<LeaveRequest> findAllRequests(LeaveStatus pending);
}
