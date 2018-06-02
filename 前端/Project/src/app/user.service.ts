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
  user: User = new User();
  private loginUrl = 'http://localhost:8081/login';
  private registerUrl = 'http://localhost:8081/register';
  constructor(
    private http: HttpClient
  ) { }

  login(): Observable<boolean> {
    return this.http.post<boolean>(this.loginUrl, this.user, httpOptions);
  }

  register(): Observable<boolean> {
    return this.http.post<boolean>(this.registerUrl, this.user, httpOptions);
  }

  setUser(user_name, user_pwd, identity) {
    this.user.user_name = user_name;
    this.user.user_pwd = user_pwd;
    this.user.identity = identity;
  }
}
