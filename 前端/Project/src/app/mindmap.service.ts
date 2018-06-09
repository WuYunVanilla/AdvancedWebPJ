import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class MindmapService {

    private baseUrl = 'http://10.222.129.245:8081/';
    tempUrl: string;

    constructor(
        private http: HttpClient) {

    }

    getMindList(course_id: string): Observable<string[]> {
        this.tempUrl = this.baseUrl + 'mindmap_id_list/' + course_id;
        return this.http.get<string[]>(this.tempUrl);
    }

    getMind(course_id: string, mind_id): Observable<string> {
        this.tempUrl = this.baseUrl + 'mindmap/' + course_id + '/' + mind_id;
        return this.http.get<string>(this.tempUrl);
    }

    // 返回值为根的信息
    createMind(course_id: string, mind_id: string): Observable<any> {
        this.tempUrl = this.baseUrl + 'mindmap/' + course_id + '/' + mind_id; // ???????
        return this.http.get<any>(this.tempUrl);
    }

    saveMind(course_id: string, mind_id: string, data: string): Observable<string> {
        this.tempUrl = this.baseUrl + 'save_mindmap/' + course_id + '/' + mind_id;
        return this.http.post<string>(this.tempUrl, data);
    }


}
