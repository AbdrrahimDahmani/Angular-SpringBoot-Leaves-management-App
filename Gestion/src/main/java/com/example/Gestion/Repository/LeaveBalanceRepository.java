package com.example.Gestion.Repository;

import com.example.Gestion.Models.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
}
