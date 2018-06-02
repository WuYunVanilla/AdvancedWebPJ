import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user_name: string;
  user_pwd: string;
  identity: string;
  confirmedPsd: string;

  constructor(public activeModal: NgbActiveModal, private userService: UserService) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    this.userService.setUser(this.user_name, this.user_pwd, this.identity);
    // this.userService.register()
    //   .subscribe((value => this.checkSuccess(value)));
    this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.alert('注册成功!');
      window.location.href = 'http://localhost:4200/courses/' + this.user_name;
    } else {
      window.alert('用户名已存在!');
    }
  }
}
