import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import {Router} from '@angular/router';

@Component({
    selector: 'app-index',
    templateUrl: './index.component.html',
    styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
    isLogin: boolean;

    constructor(private modalService: NgbModal, private router: Router) { }

    ngOnInit() {
        if (window.sessionStorage.getItem('isLogin') === 'isLogin') {
          this.isLogin = true;
        } else {
          this.isLogin = false;
        }
    }
    // 打开登录组件
    openLogin() {
        this.modalService.open(LoginComponent);
    }
    // 打开注册组件
    openRegister() {
        this.modalService.open(RegisterComponent);
    }

    // 如果已经登录那么主页不显示登录、注册组件
    enterCourse() {
      if (window.sessionStorage.getItem('identity') === 'teacher') {
        this.router.navigate(['courses']);
      } else if (window.sessionStorage.getItem('identity') === 'student') {
        this.router.navigate(['stu-courses']);
      }
    }
}
