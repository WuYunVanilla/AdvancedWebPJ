import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from '../user.service';
import {User} from '../user';

@Component({
  selector: 'app-modify-password',
  templateUrl: './modify-password.component.html',
  styleUrls: ['./modify-password.component.css']
})
export class ModifyPasswordComponent implements OnInit {
  user: User = new User();
  storedPwd: string;
  inputPwd: string;

  constructor(public activeModal: NgbActiveModal, private userService: UserService) {
    this.user.user_name = window.sessionStorage.getItem('user_name');
    this.storedPwd = window.sessionStorage.getItem('user_pwd');
  }

  ngOnInit() {
  }

  closeWindow() {
    this.activeModal.close('Close click');
  }

  // 提交修改密码请求
  onSubmit() {
    // 原密码不正确
    if (this.storedPwd !== this.inputPwd) {
      window.alert('原密码错误！');
      return;
    }
    this.userService.modifyPassword(this.user)
      .subscribe((value => this.checkSuccess(value['success'])));
  }

  checkSuccess(value) {
    if (value) {
      window.sessionStorage.setItem('user_pwd', this.user.user_pwd);
      window.alert('修改成功！');
      this.closeWindow();
    } else {
      window.alert('修改失败！');
    }
  }
}
