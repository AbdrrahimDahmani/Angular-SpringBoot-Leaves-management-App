import { Component } from '@angular/core';
import { LeaveRequest } from '../../models/leave-request';
import { LeaveService } from 'src/app/Services/leave.service';
import { CookieService } from 'ngx-cookie-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-leave-request',
  templateUrl: './leave-request.component.html',
  styleUrls: ['./leave-request.component.css'],
})
export class LeaveRequestComponent {
  leaveRequest: any;
  durationInSeconds = 5;
  constructor(
    private leaveService: LeaveService,
    private cookieService: CookieService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) {
    this.leaveRequest = {
      start_date: '',
      end_date: '',
      status: 'Pending',
      leaveType: { id: 1 },
      comment: '',
      user: {
        id: this.cookieService.get('userId'),
      },
    };
  }

  onSubmit() {
    // Récupérez les données du formulaire
    console.log(this.leaveRequest);
    // Appelez la méthode createLeaveRequest du service pour envoyer les données au backend
    this.leaveService.createLeaveRequest(this.leaveRequest).subscribe(
      (response) => {
        console.log('Demande de congé enregistrée avec succès :', response);
        this._snackBar.open('Demande de congé enregistrée avec succés');
        this.router.navigate(['/myrequests']);
      },
      (error) => {
        console.error("Erreur lors de l'enregistrement des données :", error);
      }
    );
  }
}
