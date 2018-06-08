import { Injectable } from '@angular/core';
import { Course } from './course';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {Observable} from 'rxjs';
import {UserService} from './user.service';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable()
export class CourseService {
  private baseUrl = 'http://10.222.129.245:8081/';
  tempUrl: string;

  constructor(
    private http: HttpClient) {

  }

  getCourses(user_name: string, identity: string): Observable<Course[]> {
    this.tempUrl = this.baseUrl + identity + '_courses/' + user_name;
    return this.http.get<Course[]>(this.tempUrl);
  }

  addCourse(course: Course, user_name: string, identity: string): Observable<boolean> {
    this.tempUrl = this.baseUrl + 'add_course_' + identity + '/' + user_name;
    return this.http.post<boolean>(this.tempUrl, course, httpOptions);
  }

  searchCourse(): Observable<Course[]> {
    this.tempUrl = this.baseUrl + 'search_course';
    return this.http.get<Course[]>(this.tempUrl);
  }

  stuAddCourse(user_name: string, course: Course): Observable<boolean> {
    this.tempUrl = this.baseUrl + 'add_course_student/' + user_name;
    return this.http.post<boolean>(this.tempUrl, course , httpOptions);
  }
}
