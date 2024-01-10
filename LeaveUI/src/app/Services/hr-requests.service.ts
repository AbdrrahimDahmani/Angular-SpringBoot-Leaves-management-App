import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HrRequestsService {
  private apiUrl = 'http://localhost:9090/api/leave-requests';
  token: string;
  constructor(private http: HttpClient, private cookie: CookieService) {
    this.token = cookie.get('access_token');
  }
  public getHrPendingRequests(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl + '/hr/pending', {
      headers: { Authorization: `Bearer ${this.token}` },
    });
  }
  public getHrHistoryRequests(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl + '/hr/requests', {
      headers: { Authorization: `Bearer ${this.token}` },
    });
  }
  public patchRequestStatus(id: number, status: string): Observable<any> {
    return this.http.patch(this.apiUrl + '/hr/' + id + '?status=' + status, {
      headers: { Authorization: `Bearer ${this.token}` },
    });
  }
}
