import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CookieService } from 'ngx-cookie-service';
import { LeaveService } from 'src/app/Services/leave.service';

@Component({
  selector: 'app-my-requests',
  templateUrl: './my-requests.component.html',
  styleUrls: ['./my-requests.component.css'],
})
export class MyRequestsComponent implements OnInit {
  userId: number;
  // requests = [
  //   {
  //     id: 1,
  //     startDate: new Date(),
  //     endDate: new Date(),
  //     numberOfDays: 5,
  //     status: 'Pending',
  //   },
  // ];
  leaveRequests: any[] = [];
  constructor(
    private leaveService: LeaveService,
    private cookieService: CookieService
  ) {
    this.userId = Number.parseInt(this.cookieService.get('userId')!, 0);
  }

  displayedColumns: string[] = [
    'id',
    'start_date',
    'end_date',
    'status',
    'comment',
  ];
  dataSource = new MatTableDataSource<any>();
  @ViewChild(MatPaginator) matPaginator!: MatPaginator;
  ngOnInit(): void {
    this.leaveService.getRequestsByUserId(this.userId).subscribe({
      next: (data) => {
        this.dataSource.data = data;
        if (this.matPaginator) {
          this.dataSource.paginator = this.matPaginator;
        }
      },
      error: (err) => console.log(err),
    });
  }
}
