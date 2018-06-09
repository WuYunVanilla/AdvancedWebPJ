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

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  user_name: string;
  courses: Course[];
  // courses: Course[] = new Course()[4];
  // course1: Course = new Course();
  // course2: Course = new Course();
  // course3: Course = new Course();
  // course4: Course = new Course();

  constructor(
    private modalService: NgbModal,
    private route: ActivatedRoute,
    private location: Location,
    private courseService: CourseService
  ) {
    this.user_name = window.sessionStorage.getItem('user_name');
    courseService.getCourses(window.sessionStorage.getItem('user_name'), window.sessionStorage.getItem('identity')).subscribe(
      value => this.setCourses(value));

    // this.course1.course_name = '离散数学';
    // this.course1.course_number = '2';
    // this.course1.course_id = '25';
    //
    // this.course2.course_name = '数据结构';
    // this.course2.course_number = '3';
    // this.course2.course_id = '26';
    //
    // this.course3.course_name = '计算机网络';
    // this.course3.course_number = '4';
    // this.course3.course_id = '27';
    //
    // this.course4.course_name = '高级WEB';
    // this.course4.course_number = '5';
    // this.course4.course_id = '28';
    //
    // this.courses = [this.course1, this.course2, this.course3, this.course4];
  }

  ngOnInit() {
  }
  setCourses(value) {
    this.courses = value;
  }
  addCourse(): void {
    if (window.sessionStorage.getItem('identity') === 'teacher') {
      this.modalService.open(AddCourseComponent);
    } else if (window.sessionStorage.getItem('identity') === 'student') {
      console.log('student');
      this.modalService.open(StuAddCourseComponent);
    }
  }
  enterCourse(course_id: string) {
    window.sessionStorage.setItem('course_id', course_id);
    window.location.href = 'http://localhost:4200/main';
  }
  modifyPwd(): void {
    this.modalService.open(ModifyPasswordComponent);
  }
}
