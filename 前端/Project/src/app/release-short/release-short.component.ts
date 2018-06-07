import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {NodeService} from '../node.service';
import {ShortQuestion} from '../short-question';

@Component({
  selector: 'app-release-short',
  templateUrl: './release-short.component.html',
  styleUrls: ['./release-short.component.css']
})
export class ReleaseShortComponent implements OnInit {
  short: ShortQuestion = new ShortQuestion();

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
    // this.nodeService.releaseShort(window.sessionStorage.getItem('user_name'), this.short)
    //   .subscribe((value => this.checkSuccess(value)));
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
