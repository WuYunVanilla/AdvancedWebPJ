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
    private loginUrl = '';
    private sendCodeUrl = '';
    private registerUrl = '';
    private modifyPwdUrl = '';

    constructor(
        private http: HttpClient
    ) {
        const ip = window.sessionStorage.getItem('ip');

        this.loginUrl = 'http://' + ip + ':8081/login';
        this.sendCodeUrl = 'http://' + ip + ':8081/register';
        this.registerUrl = 'http://' + ip + ':8081/transfer';
        this.modifyPwdUrl = 'http://' + ip + ':8081/modify_password';
    }

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
