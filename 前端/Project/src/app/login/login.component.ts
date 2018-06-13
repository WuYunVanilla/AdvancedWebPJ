import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../user.service';
import {User} from '../user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();

  constructor(public activeModal: NgbActiveModal, private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  closeWindow() {
    this.activeModal.close('Close click');
  }

  // 提交登录请求
  onSubmit() {
    this.userService.login(this.user)
      .subscribe((value => this.checkSuccess(value['success'])));
  }

  // 检查是否登录成功
  checkSuccess(value) {
    // 登录成功
    if (value) {
      window.sessionStorage.setItem('user_name', this.user.user_name);
      window.sessionStorage.setItem('identity', this.user.identity);
      window.sessionStorage.setItem('user_pwd', this.user.user_pwd);
      window.sessionStorage.setItem('isLogin', 'isLogin');
      this.closeWindow();
      // 根据用户身份跳转到对应的课程页
      if (this.user.identity === 'teacher') {
        this.router.navigate(['courses']);
      } else if (this.user.identity === 'student') {
        this.router.navigate(['stu-courses']);
      }
    } else {
      window.alert('登录失败!');
    }
  }
}
