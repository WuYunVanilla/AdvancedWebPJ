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
  userId: string;
  private getCoursesUrl;
  private addCourseUrl = 'http://localhost:8081/add_course/';

  constructor(
    private http: HttpClient) {

  }

  getCourses(): Observable<Course[]> {
    this.getCoursesUrl = 'http://localhost:8081/teacher_courses/' + this.userId;
    console.log(this.getCoursesUrl);
    return this.http.get<Course[]>(this.getCoursesUrl);
  }

  addCourse(course: Course): Observable<boolean> {
    this.addCourseUrl = 'http://localhost:8081/add_course/' + this.userId;
    console.log(this.addCourseUrl);
    return this.http.post<boolean>(this.addCourseUrl, course, httpOptions);
  }

  setUserId(userId: string) {
    this.userId = userId;
  }
}
