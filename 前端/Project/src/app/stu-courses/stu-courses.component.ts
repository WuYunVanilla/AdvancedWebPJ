import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {CourseService} from '../course.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModifyPasswordComponent} from '../modify-password/modify-password.component';
import {ActivatedRoute, Router} from '@angular/router';
import {Course} from '../course';
import '../../assets/bootstrap/js/bootstrap.js';

@Component({
  selector: 'app-stu-courses',
  templateUrl: './stu-courses.component.html',
  styleUrls: ['./stu-courses.component.css']
})
export class StuCoursesComponent implements OnInit {
  user_name: string;
  courses: Course[];
  allCourse: Course[];

  constructor(
    private modalService: NgbModal,
    private route: ActivatedRoute,
    private location: Location,
    private courseService: CourseService,
    private router: Router
  ) { }

  ngOnInit() {
    this.user_name = window.sessionStorage.getItem('user_name');
    this.getCourses();
    this.getAllCourse();
  }

  // 获取学生选课列表
  getCourses() {
    this.courseService.getCourses(window.sessionStorage.getItem('user_name'), window.sessionStorage.getItem('identity')).subscribe(
      value => this.setCourses(value));
  }

  setCourses(value) {
    this.courses = value;
  }

  // 获取所有课程列表
  getAllCourse() {
    this.courseService.searchCourse()
      .subscribe((value => this.setAllCourse(value)));
  }

  setAllCourse(value) {
    this.allCourse = value;
  }

  // 进入课程对应的学生思维导图页
  enterCourse(course_id: string) {
    window.sessionStorage.setItem('course_id', course_id);
    this.router.navigate(['stu-main']);
  }

  // 修改密码
  modifyPwd(): void {
    this.modalService.open(ModifyPasswordComponent);
  }

  // 登出，清除sessionStorage
  loginOut(): void {
    window.sessionStorage.clear();
    this.router.navigate(['']);
  }

  // 提交选课
  onSubmit(course: Course) {
    this.courseService.stuAddCourse(window.sessionStorage.getItem('user_name'), course)
      .subscribe((value => this.checkSuccess(value['success'])));
  }

  // 检查选课结果
  checkSuccess(value) {
    if (value) {
      window.alert('添加成功!');
      this.getCourses();
    } else {
      window.alert('添加失败!');
    }
  }

}
