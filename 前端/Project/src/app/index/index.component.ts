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
        // window.sessionStorage.setItem('ip', '10.222.129.245');
        window.sessionStorage.setItem('ip', '192.168.1.124');
    }
    openLogin() {
        this.modalService.open(LoginComponent);
    }
    openRegister() {
        this.modalService.open(RegisterComponent);
    }
}
