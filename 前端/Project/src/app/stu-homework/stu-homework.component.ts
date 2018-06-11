import { Component, OnInit } from '@angular/core';
import {NodeService} from '../node.service';
import {MultipleQuestion} from '../multiple-question';
import {ShortQuestion} from '../short-question';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {StuMultiple} from '../stu-multiple';
import {StuShort} from '../stu-short';

@Component({
  selector: 'app-stu-homework',
  templateUrl: './stu-homework.component.html',
  styleUrls: ['./stu-homework.component.css']
})
export class StuHomeworkComponent implements OnInit {
  stuMultiples: StuMultiple[];
  stuShorts: ShortQuestion[];

  constructor(
    private modalService: NgbModal,
    private nodeService: NodeService
  ) { }

  ngOnInit() {
    this.nodeService.getStuMultiple(
      window.sessionStorage.getItem('course_id'),
      window.sessionStorage.getItem('mindmap_id'),
      window.sessionStorage.getItem('node_id')).subscribe(
        value => this.setMultiple(value));
    this.nodeService.getShort(
      window.sessionStorage.getItem('course_id'),
      window.sessionStorage.getItem('mindmap_id'),
      window.sessionStorage.getItem('node_id')).subscribe(
      value => this.setShort(value));
  }

  setMultiple(value) {
    this.stuMultiples = value;
  }

  setShort(value) {
    this.stuShorts = value;
  }

  // 提交选择题
  submitMultiple(stuMultiple: StuMultiple) {
    this.nodeService.answerMultiple(
      window.sessionStorage.getItem('course_id'),
      window.sessionStorage.getItem('mindmap_id'),
      window.sessionStorage.getItem('node_id'),
      stuMultiple).subscribe(
      value => this.checkSubmit(value['success']));
  }

  // 提交简答题
  submitShort(stuShort: ShortQuestion) {
    alert('提交成功！');
  }

  // 检查提交
  checkSubmit(value) {
    if (value) {
      alert('提交成功！');
    } else {
      alert('提交失败！');
    }
  }
}
