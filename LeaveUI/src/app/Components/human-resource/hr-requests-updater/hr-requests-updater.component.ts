import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { HrRequestsService } from 'src/app/Services/hr-requests.service';

@Component({
  selector: 'app-hr-requests-updater',
  templateUrl: './hr-requests-updater.component.html',
  styleUrls: ['./hr-requests-updater.component.css'],
})
export class HrRequestsUpdaterComponent {
  status: string;
  id: number;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private hrRequestService: HrRequestsService,
    private _snackBar: MatSnackBar,
    private router: Router,
    private dialog: MatDialog
  ) {
    this.status = data.status;
    this.id = data.id;
  }

  changeStatus(event: any) {
    this.status = event.value;
  }

  submitStatus() {
    if (this.status) {
      this.hrRequestService.patchRequestStatus(this.id, this.status).subscribe({
        next: (res) => {
          console.log(res);

          this._snackBar.open('Status updated successfully', 'OK', {
            duration: 3000,
          });
          this.dialog.closeAll();
          this.router.navigate(['/requests-history']);
        },
      });
    }
  }
}
