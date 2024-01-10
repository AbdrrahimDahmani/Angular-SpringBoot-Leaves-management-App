package com.example.Gestion.Repository;

import com.example.Gestion.Models.LeaveType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

}
