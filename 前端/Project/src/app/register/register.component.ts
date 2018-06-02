import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  confirmedPsd: string;

  constructor(public activeModal: NgbActiveModal, private userService: UserService) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    // console.log(value);
    // this.httpRequestService.httpPost('http://54.201.190.180:8081/register', value, this);
    alert(this.userService.register());
  }
}
