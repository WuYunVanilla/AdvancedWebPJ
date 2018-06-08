import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Course} from '../course';
import {CourseService} from '../course.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {
  course: Course = new Course;

  constructor(
    public activeModal: NgbActiveModal,
    private courseService: CourseService) {
  }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    this.courseService.addCourse(this.course, window.sessionStorage.getItem('user_name'), window.sessionStorage.getItem('identity'))
      .subscribe((value => this.checkSuccess(value['success'])));
    // this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.alert('添加成功!');
      this.closeWindow();
      window.location.href = 'http://localhost:4200/courses';
    } else {
      window.alert('课程Id已存在!');
    }
  }
}
