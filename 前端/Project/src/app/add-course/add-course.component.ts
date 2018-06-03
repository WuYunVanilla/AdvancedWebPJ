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
    this.course.course_number = '0';
  }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    console.log(this.course.course_id);
    console.log(this.course.course_name);
    console.log(this.course.course_number);
    // this.courseService.addCourse(this.course)
    //   .subscribe((value => this.checkSuccess(value)));
    this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.alert('添加成功!');
      this.closeWindow();
    } else {
      window.alert('课程Id已存在!');
    }
  }
}
