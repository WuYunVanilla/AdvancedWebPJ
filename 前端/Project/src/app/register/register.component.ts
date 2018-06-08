import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { UserService } from '../user.service';
import {User} from '../user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  confirmedPsd: string;

  constructor(public activeModal: NgbActiveModal, private userService: UserService) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    this.userService.register(this.user)
      .subscribe((value => this.checkSuccess(value['success'])));
    // this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.sessionStorage.setItem('username', this.user.user_name);
      window.sessionStorage.setItem('identity', this.user.identity);
      window.alert('注册成功!');
      window.location.href = 'http://localhost:4200/courses';
    } else {
      window.alert('用户名已存在!');
    }
  }
}
