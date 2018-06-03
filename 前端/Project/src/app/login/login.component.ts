import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user_name: string;
  user_pwd: string;
  identity: string;

  constructor(public activeModal: NgbActiveModal, private userService: UserService) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    this.userService.setUser(this.user_name, this.user_pwd, this.identity);
    // this.userService.login()
    //   .subscribe((value => this.checkSuccess(value)));
    this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.location.href = 'http://localhost:4200/courses/' + this.user_name;
    } else {
      window.alert('用户名与密码不匹配!');
    }
  }
}
