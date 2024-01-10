import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LeaveService } from 'src/app/Services/leave.service';
import { CookieService } from 'ngx-cookie-service';
import { LeaveHistoryService } from 'src/app/Services/leave-history.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  userId: number;
  leaveRequestNumber: number;
  approvedLeaveRequestNumber: number;
  declinedLeaveRequestNumber: number;
  constructor(
    private leaveService: LeaveService,
    private cookieService: CookieService,
    private leaveHistoryService: LeaveHistoryService
  ) {
    this.userId = Number.parseInt(this.cookieService.get('userId')!, 0);
    this.leaveRequestNumber = 0;
    this.approvedLeaveRequestNumber = 0;
    this.declinedLeaveRequestNumber = 0;
  }

  ngOnInit(): void {
    this.leaveService.getRequestsByUserId(this.userId).subscribe({
      next: (data) => {
        this.leaveRequestNumber = Number(data.length);
      },
      error: (err) => console.log(err),
    });

    this.leaveHistoryService.getLeaveHistoryData(this.userId).subscribe({
      next: (data) => {
        this.approvedLeaveRequestNumber = data.filter((leave) => {
          return leave.status == 'Approved';
        }).length;
        this.declinedLeaveRequestNumber = data.filter((leave) => {
          return leave.status == 'Declined';
        }).length;
      },
      error: (err) => console.log(err),
    });
  }
}
