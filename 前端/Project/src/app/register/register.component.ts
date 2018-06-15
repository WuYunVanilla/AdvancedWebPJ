import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { UserService } from '../user.service';
import {RegisterUser} from '../register-user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: RegisterUser = new RegisterUser();
  confirmedPsd: string;
  isChecking = false;

  next = false;

  constructor(public activeModal: NgbActiveModal, private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  closeWindow() {
    this.activeModal.close('Close click');
  }

  // 验证码界面
  goToNext() {
    this.next = true;
  }

  // 填写用户名、密码界面
  goBack() {
    this.next = false;
  }

  // 发送验证码
  sendCode() {
    this.isChecking = true;
    this.userService.sendCode(this.user)
      .subscribe((value => this.checkRegister(value['success'])));
  }

  // 提交注册表
  onSubmit() {
    this.userService.register(this.user)
      .subscribe((value => this.checkSuccess(value['success'])));
  }

  // 检查上述内容填写是否有误
  checkRegister(value) {
    if (value) {
      window.alert('验证码已发送！');
    } else {
      window.alert('用户名或邮箱重复！');
    }
    this.isChecking = false;
  }

  // 检查注册是否成功
  checkSuccess(value) {
    if (value) {
      window.sessionStorage.setItem('user_name', this.user.user_name);
      window.sessionStorage.setItem('identity', this.user.identity);
      window.sessionStorage.setItem('user_pwd', this.user.user_pwd);
      window.sessionStorage.setItem('isLogin', 'isLogin');
      window.alert('注册成功!');
      this.closeWindow();
      // 根据用户身份跳转到对应的课程页
      if (this.user.identity === 'teacher') {
        this.router.navigate(['courses']);
      } else if (this.user.identity === 'student') {
        this.router.navigate(['stu-courses']);
      }
    } else {
      window.alert('注册失败!');
    }
  }
}
