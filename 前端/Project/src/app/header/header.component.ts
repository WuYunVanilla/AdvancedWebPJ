import { Component, OnInit } from '@angular/core';
import {ModifyPasswordComponent} from '../modify-password/modify-password.component';
import {Router} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user_name: string;

  constructor(private modalService: NgbModal,
              private router: Router) { }

  ngOnInit() {
    this.user_name = window.sessionStorage.getItem('user_name');
  }

  // 修改密码
  modifyPwd(): void {
    this.modalService.open(ModifyPasswordComponent);
  }

  // 登出，清除sessionStorage
  loginOut(): void {
    window.sessionStorage.clear();
    this.router.navigate(['']);
  }

}
