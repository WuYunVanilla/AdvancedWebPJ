import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {CourseService} from '../course.service';
import {Course} from '../course';

@Component({
  selector: 'app-stu-add-course',
  templateUrl: './stu-add-course.component.html',
  styleUrls: ['./stu-add-course.component.css']
})
export class StuAddCourseComponent implements OnInit {
  courses: Course[];
  course: Course;

  constructor(
    public activeModal: NgbActiveModal,
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.courseService.searchCourse()
      .subscribe((value => this.setCourses(value)));
  }

  closeWindow() {
    this.activeModal.close('Close click');
    window.location.href = 'http://localhost:4200/courses';
  }
  setCourses(value) {
    this.courses = value;
  }
  onSubmit(course: Course) {
    this.courseService.stuAddCourse(window.sessionStorage.getItem('user_name'), course)
      .subscribe((value => this.checkSuccess(value['success'])));
    // this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.alert('添加成功!');
    } else {
      window.alert('添加失败!');
    }
  }
}
