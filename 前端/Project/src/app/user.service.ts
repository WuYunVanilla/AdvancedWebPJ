import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {jsonpCallbackContext} from '@angular/common/http/src/module';
import {RegisterUser} from './register-user';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable()
export class UserService {
  private loginUrl = 'http://10.222.129.245:8081/login';
  private sendCodeUrl = 'http://10.222.129.245:8081/register';
  private registerUrl = 'http://10.222.129.245:8081/transfer';
  private modifyPwdUrl = 'http://10.222.129.245:8081/modify_password';
  constructor(
    private http: HttpClient
  ) { }

  login(user: User): Observable<boolean> {
    return this.http.post<boolean>(this.loginUrl, user, httpOptions);
  }

  sendCode(user: RegisterUser): Observable<boolean> {
    return this.http.post<boolean>(this.sendCodeUrl, user, httpOptions);
  }

  register(user: RegisterUser): Observable<boolean> {
    return this.http.post<boolean>(this.registerUrl, user, httpOptions);
  }

  modifyPassword(user: User): Observable<boolean> {
    return this.http.post<boolean>(this.modifyPwdUrl, user, httpOptions);
  }
}
