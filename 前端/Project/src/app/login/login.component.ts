import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../user.service';
import {User} from '../user';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();

  constructor(public activeModal: NgbActiveModal, private userService: UserService) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }
  onSubmit() {
    this.userService.login(this.user)
      .subscribe((value => this.checkSuccess(value['success'])));
    // this.checkSuccess(true);
  }
  checkSuccess(value) {
    if (value) {
      window.sessionStorage.setItem('user_name', this.user.user_name);
      window.sessionStorage.setItem('identity', this.user.identity);
      window.sessionStorage.setItem('user_pwd', this.user.user_pwd);
      window.location.href = 'http://' + environment.id + ':' + environment.port + '/courses';
    } else {
      window.alert('登录失败!');
    }
  }
}
