import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {LoginComponent} from '../login/login.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  constructor(private modalService: NgbModal) { }

  ngOnInit() {
  }
  openLogin() {
    this.modalService.open(LoginComponent);
  }
  openRegister() {
    this.modalService.open(RegisterComponent);
  }
}
