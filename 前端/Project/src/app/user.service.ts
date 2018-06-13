import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {jsonpCallbackContext} from '@angular/common/http/src/module';
import {RegisterUser} from './register-user';
import {environment} from '../environments/environment';

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
        const url = environment.apiUrl;

        this.loginUrl = url + 'login';
        this.sendCodeUrl = url + 'register';
        this.registerUrl = url + 'transfer';
        this.modifyPwdUrl = url + 'modify_password';
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
