import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { CookieService } from 'ngx-cookie-service';
import { HrRequestsService } from 'src/app/Services/hr-requests.service';
import { LeaveService } from 'src/app/Services/leave.service';
import { HrRequestsUpdaterComponent } from '../hr-requests-updater/hr-requests-updater.component';

@Component({
  selector: 'app-hr-pending-requests',
  templateUrl: './hr-pending-requests.component.html',
  styleUrls: ['./hr-pending-requests.component.css'],
})
export class HrPendingRequestsComponent implements OnInit {
  pendingLeaveRequests: any[] = [];
  constructor(
    private hrRequestService: HrRequestsService,
    private matSnackBar: MatSnackBar,
    public dialog: MatDialog
  ) {}

  displayedColumns: string[] = [
    'id',
    'start_date',
    'end_date',
    'status',
    'type',
    'comment',
    'edit',
  ];
  leaveType: any[] = [];
  dataSource = new MatTableDataSource<any>();
  @ViewChild(MatPaginator) matPaginator!: MatPaginator;
  filterString = '';
  ngOnInit(): void {
    this.hrRequestService.getHrPendingRequests().subscribe({
      next: (data) => {
        this.dataSource.data = data;
        // this.leaveType = data.map((x) => x.leaveType.name);
        // const leaveTypeName = Object.values(this.leaveType)[1];
        // console.log(leaveTypeName); // "Medicament"
        // this.dataSource.data.push({
        //   type: leaveTypeName,
        // });
        console.log(this.dataSource.data);

        if (this.matPaginator) {
          this.dataSource.paginator = this.matPaginator;
        }
      },
      error: (err) => console.log(err),
    });
  }

  filterRequests() {
    this.dataSource.filter = this.filterString.trim().toLowerCase();
  }

  openDialog(id: number, status: string): void {
    const dialogRef = this.dialog.open(HrRequestsUpdaterComponent, {
      width: '550px',
      hasBackdrop: true,
      data: {
        id: id,
        status: status,
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }
}
