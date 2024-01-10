import { Component, OnInit, ViewChild } from '@angular/core';
import { LeaveHistoryService } from '../../Services/leave-history.service';
import { MatTableDataSource } from '@angular/material/table';
import { CookieService } from 'ngx-cookie-service';
import { LeaveService } from 'src/app/Services/leave.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-leave-history',
  templateUrl: './leave-history.component.html',
  styleUrls: ['./leave-history.component.css'],
})
export class LeaveHistoryComponent implements OnInit {
  leaveHistoryData: any[] = [];
  userId: number;
  leaveRequests: any[] = [];
  constructor(
    // private leaveService: LeaveService,
    private cookieService: CookieService,
    private leaveHistoryService: LeaveHistoryService
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
    // Utilisez le service pour récupérer les données du backend
    this.leaveHistoryService.getLeaveHistoryData(this.userId).subscribe({
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
