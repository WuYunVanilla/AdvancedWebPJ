import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable()
export class UserService {
  user: User;
  private loginUrl = 'http://localhost:8081/login';
  private registerUrl = 'http://localhost:8081/register';
  constructor(
    private http: HttpClient
  ) { }

  // login(): boolean {
  //   return this.http.post();
  // }

  register(): Observable<boolean> {
    return this.http.post<User>(this.registerUrl, this.user, httpOptions);
  }
}
