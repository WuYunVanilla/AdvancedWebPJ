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
  stuMultiples: StuMultiple[] = new StuMultiple()[2];
  stuShorts: StuShort[] = new StuShort()[2];
  multiple1: StuMultiple = new StuMultiple();
  multiple2: StuMultiple = new StuMultiple();
  short1: StuShort = new StuShort();
  short2: StuShort = new StuShort();

  constructor(
    private modalService: NgbModal,
    private nodeService: NodeService
  ) {
    // this.nodeService.getStuMultiple(
    //   window.sessionStorage.getItem('course_id'),
    //   window.sessionStorage.getItem('mindmap_id'),
    //   window.sessionStorage.getItem('node_id')).subscribe(
    //     value => this.setMultiple(value));
    // this.nodeService.getStuShort(
    //   window.sessionStorage.getItem('course_id'),
    //   window.sessionStorage.getItem('mindmap_id'),
    //   window.sessionStorage.getItem('node_id')).subscribe(
    //   value => this.setShort(value));

    this.multiple1.title = '请选择世界上最好用的语言';
    this.multiple1.optionA = 'java';
    this.multiple1.optionB = 'php';
    this.multiple1.optionC = 'c++';
    this.multiple1.optionD = 'python';

    this.multiple2.title = '请选择世界上最好吃的水果';
    this.multiple2.optionA = '西瓜';
    this.multiple2.optionB = '苹果';
    this.multiple2.optionC = '凤梨';
    this.multiple2.optionD = '桃子';

    this.stuMultiples = [this.multiple1, this.multiple2];

    this.short1.title = '简述你最喜欢的xxx';

    this.short2.title = '高级web这节课好在哪里';

    this.stuShorts = [this.short1, this.short2];
  }

  ngOnInit() {
  }

  setMultiple(value) {
    this.stuMultiples = value;
  }
  setShort(value) {
    this.stuShorts = value;
  }

  submitMultiple(stuMultiple: StuMultiple) {

  }
  submitShort(stuShort: StuShort) {

  }
}
