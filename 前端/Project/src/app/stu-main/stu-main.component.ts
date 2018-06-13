import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-stu-main',
  templateUrl: './stu-main.component.html',
  styleUrls: ['./stu-main.component.css']
})
export class StuMainComponent implements OnInit {
  user_name: string;

  constructor(private router: Router) { }

  ngOnInit() {
    this.user_name = window.sessionStorage.getItem('user_name');
  }

  // 登出，清除sessionStorage
  loginOut(): void {
    window.sessionStorage.clear();
    this.router.navigate(['']);
  }
}
