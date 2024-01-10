package com.example.Gestion.Repository;

import com.example.Gestion.Models.LeaveHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Long> {
}
