import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class MindmapService {

    private baseUrl = '';

    tempUrl: string;

    constructor(
        private http: HttpClient) {

        this.baseUrl = window.sessionStorage.getItem('url');
    }

    getMindList(course_id: string): Observable<any> {
        this.tempUrl = this.baseUrl + 'mindmap_id_list/' + course_id;
        return this.http.get<any>(this.tempUrl);
    }

    getMind(course_id: string, mind_id: string): Observable<any> {
        console.log('in getMind: mind_id=' + mind_id);
        this.tempUrl = this.baseUrl + 'mindmap/' + course_id + '/' + mind_id;
        return this.http.get<any>(this.tempUrl);
    }

    // 返回值为根的信息
    createMind(course_id: string, mind_id: string, data: string): Observable<any> {
        return this.saveMind(course_id, mind_id, data);
    }

    saveMind(course_id: string, mind_id: string, data: string): Observable<any> {
        this.tempUrl = this.baseUrl + 'save_mindmap/' + course_id + '/' + mind_id;
        return this.http.post<any>(this.tempUrl, data);
    }


}
