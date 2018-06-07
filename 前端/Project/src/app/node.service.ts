import { Injectable } from '@angular/core';
import {Course} from './course';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {MultipleQuestion} from './multiple-question';
import {ShortQuestion} from './short-question';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class NodeService {
  private baseUrl = 'http://localhost:8081/';
  tempUrl: string;

  constructor(
    private http: HttpClient) {

  }

  getMultiple(user_name: string, identity: string): Observable<Course[]> {
    this.tempUrl = this.baseUrl + identity + '_courses/' + user_name;
    return this.http.get<Course[]>(this.tempUrl);
  }

  getShort(user_name: string, identity: string): Observable<Course[]> {
    this.tempUrl = this.baseUrl + identity + '_courses/' + user_name;
    return this.http.get<Course[]>(this.tempUrl);
  }

  releaseMutiple(user_name: string, multiple: MultipleQuestion): Observable<boolean> {
    return this.http.post<boolean>(this.tempUrl, multiple, httpOptions);
  }

  releaseShort(user_name: string, short: ShortQuestion): Observable<boolean> {
    return this.http.post<boolean>(this.tempUrl, short, httpOptions);
  }
}
