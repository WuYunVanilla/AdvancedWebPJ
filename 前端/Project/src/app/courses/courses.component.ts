import { Component, OnInit } from '@angular/core';
import {CourseService} from '../course.service';
import {Course} from '../course';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {LoginComponent} from '../login/login.component';
import {AddCourseComponent} from '../add-course/add-course.component';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {StuAddCourseComponent} from '../stu-add-course/stu-add-course.component';
import {ModifyPasswordComponent} from '../modify-password/modify-password.component';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  user_name: string;
  courses: Course[];

  constructor(
    private modalService: NgbModal,
    private route: ActivatedRoute,
    private location: Location,
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.user_name = window.sessionStorage.getItem('user_name');
    // 获取课程列表
    this.courseService.getCourses(window.sessionStorage.getItem('user_name'), window.sessionStorage.getItem('identity')).subscribe(
      value => this.setCourses(value));
  }
  setCourses(value) {
    this.courses = value;
  }

  // 添加课程，如果是老师，打开新建课程组件；如果是学生，打开选课组件
  addCourse(): void {
    if (window.sessionStorage.getItem('identity') === 'teacher') {
      this.modalService.open(AddCourseComponent);
    } else if (window.sessionStorage.getItem('identity') === 'student') {
      console.log('student');
      this.modalService.open(StuAddCourseComponent);
    }
  }

  // 进入课程对应的思维导图页
  enterCourse(course_id: string) {
    window.sessionStorage.setItem('course_id', course_id);
    if (window.sessionStorage.getItem('identity') === 'teacher') {
      window.location.href = 'http://' + environment.id + ':' + environment.port + '/main';
    } else if (window.sessionStorage.getItem('identity') === 'student') {
      window.location.href = 'http://' + environment.id + ':' + environment.port + '/stu-main';
    }
  }

  modifyPwd(): void {
    this.modalService.open(ModifyPasswordComponent);
  }

  // 登出，清除sessionStorage
  loginOut(): void {
    window.sessionStorage.clear();
    window.location.href = 'http://' + environment.id + ':' + environment.port + '/';
  }
}
