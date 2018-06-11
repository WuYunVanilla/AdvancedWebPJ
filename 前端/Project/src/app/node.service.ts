import { Injectable } from '@angular/core';
import {Course} from './course';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {MultipleQuestion} from './multiple-question';
import {ShortQuestion} from './short-question';
import {StuMultiple} from './stu-multiple';
import {StuShort} from './stu-short';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class NodeService {
    private baseUrl = '';
    tempUrl: string;

    constructor(
        private http: HttpClient) {
        const ip = window.sessionStorage.getItem('ip');
        this.baseUrl = 'http://' + ip + ':8081/';
    }

    // 教师获取选择题列表
    getMultiple(course_id: string, mindmap_id: string, node_id: string): Observable<MultipleQuestion[]> {
        this.tempUrl = this.baseUrl + 'multiples_teacher/' + course_id + '/' + mindmap_id + '/' + node_id;
        return this.http.get<MultipleQuestion[]>(this.tempUrl);
    }

    // 教师&学生获取简答题列表
    getShort(course_id: string, mindmap_id: string, node_id: string): Observable<ShortQuestion[]> {
        this.tempUrl = this.baseUrl + 'shorts/' + course_id + '/' + mindmap_id + '/' + node_id;
        return this.http.get<ShortQuestion[]>(this.tempUrl);
    }

    // 教师发布选择题
    releaseMutiple(course_id: string, mindmap_id: string, node_id: string, multiple: MultipleQuestion): Observable<boolean> {
        this.tempUrl = this.baseUrl + 'release_multiple/' + course_id + '/' + mindmap_id + '/' + node_id;
        return this.http.post<boolean>(this.tempUrl, multiple, httpOptions);
    }

    // 教师发布简答题
    releaseShort(course_id: string, mindmap_id: string, node_id: string, short: ShortQuestion): Observable<boolean> {
        this.tempUrl = this.baseUrl + 'release_short/' + course_id + '/' + mindmap_id + '/' + node_id;
        return this.http.post<boolean>(this.tempUrl, short, httpOptions);
    }

    // 学生获取选择题列表
    getStuMultiple(course_id: string, mindmap_id: string, node_id: string): Observable<StuMultiple[]> {
        this.tempUrl = this.baseUrl + 'multiples_student/' + course_id + '/' + mindmap_id + '/' + node_id;
        return this.http.get<StuMultiple[]>(this.tempUrl);
    }

    // 学生回答选择题
    answerMultiple(course_id: string, mindmap_id: string, node_id: string, stuMultiple: StuMultiple): Observable<boolean> {
        this.tempUrl = this.baseUrl + 'answer_multiple/' + course_id + '/' + mindmap_id + '/' + node_id;
        return this.http.post<boolean>(this.tempUrl, stuMultiple, httpOptions);
    }

    // 获取资源列表
    getResourses(course_id: string, mind_id: string, node_id: string): Observable<string[]> {
        this.tempUrl = this.baseUrl + 'materials/' + course_id + '/' + mind_id + '/' + node_id;
        return this.http.get<string[]>(this.tempUrl);
    }

    getLinkResourses(course_id: string, mind_id: string, node_id: string): Observable<string[]> {
        this.tempUrl = this.baseUrl + 'links/' + course_id + '/' + mind_id + '/' + node_id;
        return this.http.get<string[]>(this.tempUrl);
    }

    upload_link(course_id: string, mind_id: string, node_id: string, link_addr: string): Observable<boolean> {
        this.tempUrl = this.baseUrl + 'upload_link/' + course_id + '/' + mind_id + '/' + node_id;
        const target = {'link_address': link_addr};
        return this.http.post<boolean>(this.tempUrl, target, httpOptions);
    }

    downloadResource(course_id: string, mind_id: string, node_id: string, resource_name: string): Observable<any> {

        // this.tempUrl = this.baseUrl + 'download_material/' + course_id + '/' + mind_id + '/' + node_id;
        //
        // console.log(this.tempUrl);
        //
        // const body = {'material_name': resource_name};
        //
        // const options = new HttpHeaders({'Content-Type': 'application/force-download'});
        //
        // return this.http.post<any>(this.tempUrl, body, options);
      return;
    }

}
