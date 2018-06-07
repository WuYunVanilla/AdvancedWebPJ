import { Component, OnInit } from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AddCourseComponent} from '../add-course/add-course.component';
import {Course} from '../course';
import {CourseService} from '../course.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {MultipleQuestion} from '../multiple-question';
import {ShortQuestion} from '../short-question';
import {MindmapService} from '../mindmap.service';
import {NodeService} from '../node.service';

@Component({
  selector: 'app-homework',
  templateUrl: './homework.component.html',
  styleUrls: ['./homework.component.css']
})
export class HomeworkComponent implements OnInit {
  multipleQuestion: MultipleQuestion[] = new MultipleQuestion()[2];
  shortQuestion: ShortQuestion[] = new ShortQuestion()[2];
  multiple1: MultipleQuestion = new MultipleQuestion();
  multiple2: MultipleQuestion = new MultipleQuestion();
  short1: ShortQuestion = new ShortQuestion();
  short2: ShortQuestion = new ShortQuestion();


  constructor(
    private modalService: NgbModal,
    private nodeService: NodeService
  ) {
    // this.nodeService.getMultiple(
    //   window.sessionStorage.getItem('user_name'),
    //   window.sessionStorage.getItem('course_id'),
    //   window.sessionStorage.getItem('mindmap_id'),
    //   window.sessionStorage.getItem('node_id')).subscribe(
    //     value => this.setMultiple(value));
    // this.nodeService.getShort(
    //   window.sessionStorage.getItem('user_name'),
    //   window.sessionStorage.getItem('course_id'),
    //   window.sessionStorage.getItem('mindmap_id'),
    //   window.sessionStorage.getItem('node_id')).subscribe(
    //   value => this.setShort(value));

    this.multiple1.title = '请选择世界上最好用的语言';
    this.multiple1.optionA = 'java';
    this.multiple1.optionB = 'php';
    this.multiple1.optionC = 'c++';
    this.multiple1.optionD = 'python';
    this.multiple1.correct_answer = 'A';
    this.multiple1.correct_number = '10';
    this.multiple1.number = '20';

    this.multiple2.title = '请选择世界上最好吃的水果';
    this.multiple2.optionA = '西瓜';
    this.multiple2.optionB = '苹果';
    this.multiple2.optionC = '凤梨';
    this.multiple2.optionD = '桃子';
    this.multiple2.correct_answer = 'C';
    this.multiple2.correct_number = '5';
    this.multiple2.number = '25';

    this.multipleQuestion = [this.multiple1, this.multiple2];

    this.multiple1.title = '简述';
    this.multiple1.correct_answer = 'A';

    this.multiple2.title = '请选择世界上最好吃的水果';
    this.multiple2.optionA = '西瓜';
    this.multiple2.optionB = '苹果';
    this.multiple2.optionC = '凤梨';
    this.multiple2.optionD = '桃子';
    this.multiple2.correct_answer = 'C';
    this.multiple2.correct_number = '5';
    this.multiple2.number = '25';

    this.multipleQuestion = [this.multiple1, this.multiple2];

  }

  ngOnInit() {
  }
  setMultiple(value) {
    this.multipleQuestion = value;
  }
  setShort(value) {
    this.shortQuestion = value;
  }
  releaseMultiple() {

  }
  releaseShort() {

  }


  // constructor(
  //   private modalService: NgbModal,
  //   private route: ActivatedRoute,
  //   private location: Location,
  //   private courseService: CourseService
  // ) {
  //   courseService.getCourses(window.sessionStorage.getItem('user_name'), window.sessionStorage.getItem('identity')).subscribe(
  //     value => this.setCourses(value));
  //
  //   this.course1.course_name = '离散数学';
  //   this.course1.course_number = '2';
  //   this.course1.course_id = '25';
  //
  //   this.course2.course_name = '数据结构';
  //   this.course2.course_number = '3';
  //   this.course2.course_id = '26';
  //
  //   this.course3.course_name = '计算机网络';
  //   this.course3.course_number = '4';
  //   this.course3.course_id = '27';
  //
  //   this.course4.course_name = '高级WEB';
  //   this.course4.course_number = '5';
  //   this.course4.course_id = '28';
  //
  //   this.courses = [this.course1, this.course2, this.course3, this.course4];
  // }
  //
  // ngOnInit() {
  // }
  // setCourses(value) {
  //   this.courses = value;
  // }
  // addCourse(): void {
  //   this.modalService.open(AddCourseComponent);
  // }
  // enterCourse(course_id: string) {
  //   window.sessionStorage.setItem('course_id', course_id);
  //   window.location.href = 'http://localhost:4200/main';
  // }
}
