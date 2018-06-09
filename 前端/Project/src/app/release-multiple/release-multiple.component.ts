import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Course} from '../course';
import {CourseService} from '../course.service';
import {MultipleQuestion} from '../multiple-question';
import {NodeService} from '../node.service';

@Component({
  selector: 'app-release-multiple',
  templateUrl: './release-multiple.component.html',
  styleUrls: ['./release-multiple.component.css']
})
export class ReleaseMultipleComponent implements OnInit {
  multiple: MultipleQuestion = new MultipleQuestion();

  constructor(
    public activeModal: NgbActiveModal,
    public nodeService: NodeService
  ) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    // this.nodeService.releaseMutiple(
    //   window.sessionStorage.getItem('course_id'),
    //   window.sessionStorage.getItem('mindmap_id'),
    //   window.sessionStorage.getItem('node_id'),
    //   this.multiple)
    //   .subscribe((value => this.checkSuccess(value['success'])));
    this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.alert('发布成功!');
      this.closeWindow();
    } else {
      window.alert('发布失败!');
    }
  }
}
