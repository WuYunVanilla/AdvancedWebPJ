import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
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
