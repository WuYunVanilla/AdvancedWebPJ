import { Injectable } from '@angular/core';
import {Course} from './course';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class NodeService {

  constructor() { }

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
}
