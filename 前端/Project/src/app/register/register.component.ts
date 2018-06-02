import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { HttpRequestService } from '../http-request.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userName: string;
  userPsd: string;
  confirmedPsd: string;

  constructor(public activeModal: NgbActiveModal, private httpRequestService: HttpRequestService) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit(value) {
    // console.log(value);
    this.httpRequestService.httpPost('http://54.201.190.180:8081/register', value, this, 'save');
  }
  postOk(val, flag) {
    alert('success');
  }
  postErr(val, flag) {
    alert('fail');
  }
}
