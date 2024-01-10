import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { HrRequestsService } from 'src/app/Services/hr-requests.service';

@Component({
  selector: 'app-hr-requests-history',
  templateUrl: './hr-requests-history.component.html',
  styleUrls: ['./hr-requests-history.component.css'],
})
export class HrRequestsHistoryComponent {
  leaveRequestsHistory: any[] = [];
  constructor(private hrRequestService: HrRequestsService) {}

  displayedColumns: string[] = [
    'id',
    'start_date',
    'end_date',
    'status',
    'comment',
  ];
  dataSource = new MatTableDataSource<any>();
  @ViewChild(MatPaginator) matPaginator!: MatPaginator;
  filterString = '';
  ngOnInit(): void {
    this.hrRequestService.getHrHistoryRequests().subscribe({
      next: (data) => {
        this.dataSource.data = data;
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
}
