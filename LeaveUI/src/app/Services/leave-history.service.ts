import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
@Injectable({
  providedIn: 'root',
})
export class LeaveHistoryService {
  private apiUrl = 'http://localhost:9090/api/';
  token: string;
  constructor(private http: HttpClient, private cookie: CookieService) {
    this.token = cookie.get('access_token');
  }
  getLeaveHistoryData(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}leave-requests/history/` + id, {
      headers: { Authorization: `Bearer ${this.token}` },
    });
  }

  saveData(data: any) {
    return this.http.post(this.apiUrl, data);
  }
}
