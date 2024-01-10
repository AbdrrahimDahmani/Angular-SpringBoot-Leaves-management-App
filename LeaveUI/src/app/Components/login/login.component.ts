import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { AuthenticationRequest } from 'src/app/models/authentication-request';
import { AuthenticationResponse } from 'src/app/models/authentication-response';
import { UserService } from 'src/app/Services/user.service';
import { HttpClient } from '@angular/common/http';
import { AuthService } from 'src/app/Services/auth.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user = new User();
  authenticationRequest: AuthenticationRequest = {
    email: '',
    password: '',
  };
  constructor(
    private router: Router,
    private http: HttpClient,
    private userservice: UserService,
    private authservice: AuthService,
    private cookieService: CookieService
  ) {}

  ngOnInit() {}

  login(event: Event) {
    event.preventDefault();

    this.userservice.login(this.authenticationRequest).subscribe({
      next: (res: any) => {
        if (res.statusCodeValue === 200) {
          console.log(res);
          this.cookieService.set('access_token', res.body.access_token, {
            expires: new Date(Date.now() + 3600000),
          });
          localStorage.setItem('access_token', res.body.access_token);
          this.cookieService.set('userId', res.body.id, {
            expires: new Date(Date.now() + 3600000),
          });
          this.cookieService.set('role', res.body.role, {
            expires: new Date(Date.now() + 3600000),
          });
          if (res.body.role === 'Employer') {
            this.router.navigate(['/dashboard']);
          } else if (res.body.role === 'human_resource') {
            this.router.navigate(['/hr-dashboard']);
          }
        }
      },
      error: (err) => console.log(err),
    });
  }
}
