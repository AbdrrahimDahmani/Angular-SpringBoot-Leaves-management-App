import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthenticationResponse } from '../models/authentication-response';


@Injectable({
  providedIn: 'root'
})
export class RegisrationService {
  private backendURL :string;
  user= new Subject<User>;


  constructor(private http:HttpClient) { this.backendURL='http://localhost:9090/users/';}

  public Register(user:User):Observable<any>{
     return this.http.post<AuthenticationResponse>(this.backendURL+"register",user)

  }
}
