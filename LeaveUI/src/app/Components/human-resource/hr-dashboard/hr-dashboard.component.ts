import { Component } from '@angular/core';
import { HrRequestsService } from 'src/app/Services/hr-requests.service';

@Component({
  selector: 'app-hr-dashboard',
  templateUrl: './hr-dashboard.component.html',
  styleUrls: ['./hr-dashboard.component.css'],
})
export class HrDashboardComponent {
  leaveRequestNumber: number;
  approvedLeaveRequestNumber: number;
  declinedLeaveRequestNumber: number;
  constructor(private hrRequestService: HrRequestsService) {
    this.leaveRequestNumber = 0;
    this.approvedLeaveRequestNumber = 0;
    this.declinedLeaveRequestNumber = 0;
  }

  ngOnInit(): void {
    this.hrRequestService.getHrPendingRequests().subscribe({
      next: (data) => {
        this.leaveRequestNumber = Number(data.length);
      },
      error: (err) => console.log(err),
    });

    this.hrRequestService.getHrHistoryRequests().subscribe({
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
