import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthenticationResponse } from '../models/authentication-response';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private backendURL = 'http://localhost:9090';
  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService,
    private cookieService: CookieService
  ) {
    this.backendURL = 'http://localhost:9090/users/';
  }
  public login(user: User): Observable<any> {
    return this.http.post<AuthenticationResponse>(
      this.backendURL + 'login',
      user
    );
  }
  isAuthenticated(): boolean {
    const token = this.cookieService.get('access_token');
    return !this.jwtHelper.isTokenExpired(token);
  }
}
