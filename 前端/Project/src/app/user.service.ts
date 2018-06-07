import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {jsonpCallbackContext} from '@angular/common/http/src/module';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable()
export class UserService {
  private loginUrl = 'http://localhost:8081/login';
  private registerUrl = 'http://localhost:8081/register';
  constructor(
    private http: HttpClient
  ) { }

  login(user: User): Observable<boolean> {
    return this.http.post<boolean>(this.loginUrl, user, httpOptions);
  }

  register(user: User): Observable<boolean> {
    return this.http.post<boolean>(this.registerUrl, user, httpOptions);
  }
}
