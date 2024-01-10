package com.example.Gestion.dto;

import com.example.Gestion.Models.LeaveType;
import com.example.Gestion.Models.User;

import java.time.LocalDate;

public class LeaveRequestDto {
    public User user;
    public LeaveType leaveType;

    public LocalDate start_date;
    public LocalDate end_date;
    public String status;
    public String comment;
}
