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
import {LoginComponent} from '../login/login.component';
import {RegisterComponent} from '../register/register.component';
import {ReleaseMultipleComponent} from '../release-multiple/release-multiple.component';
import {ReleaseShortComponent} from '../release-short/release-short.component';

@Component({
  selector: 'app-homework',
  templateUrl: './homework.component.html',
  styleUrls: ['./homework.component.css']
})
export class HomeworkComponent implements OnInit {
  multipleQuestion: MultipleQuestion[];
  shortQuestion: ShortQuestion[];

  constructor(
    private modalService: NgbModal,
    private nodeService: NodeService
  ) { }

  ngOnInit() {
    // 获取所有的选择题
    this.nodeService.getMultiple(
      window.sessionStorage.getItem('course_id'),
      window.sessionStorage.getItem('mindmap_id'),
      window.sessionStorage.getItem('node_id')).subscribe(
      value => this.setMultiple(value));

    // 获取所有的简答题
    this.nodeService.getShort(
      window.sessionStorage.getItem('course_id'),
      window.sessionStorage.getItem('mindmap_id'),
      window.sessionStorage.getItem('node_id')).subscribe(
      value => this.setShort(value));
  }
  setMultiple(value) {
    this.multipleQuestion = value;
  }
  setShort(value) {
    this.shortQuestion = value;
  }
  releaseMultiple() {
    this.modalService.open(ReleaseMultipleComponent);
  }
  releaseShort() {
    this.modalService.open(ReleaseShortComponent);
  }
}
