import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisrationService } from 'src/app/Services/regisration.service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
user:User={};


  constructor(private http: HttpClient, private registerService : RegisrationService, private router:Router) {}

  onSubmit() {
  
    this.registerService.Register(this.user).subscribe(
      response => {this.router.navigate(['/login'])
        console.log('Registration successful:', response);
        
      },
      (error) => {
      
        console.error('Erreur lors de la requête HTTP :', error);
      }
    );
  }
}

