import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationResponse } from '../models/authentication-response';
import { CookieService } from 'ngx-cookie-service';
@Injectable({
  providedIn: 'root',
})
export class LeaveService {
  private backendURL = 'http://localhost:9090/api';
  token: string;
  constructor(private http: HttpClient, private cookie: CookieService) {
    this.token = cookie.get('access_token');
  }

  createLeaveRequest(requestData: any): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
    return this.http.post(
      `${this.backendURL}/leave-requests/create`,
      requestData,
      httpOptions
    );
  }

  getLeaveHistory(): Observable<any[]> {
    return this.http.get<any[]>(`${this.backendURL}/leave-requests/history`);
  }

  getRequestsByUserId(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.backendURL}/leave-requests/` + id, {
      headers: { Authorization: `Bearer ${this.token}` },
    });
  }
}
