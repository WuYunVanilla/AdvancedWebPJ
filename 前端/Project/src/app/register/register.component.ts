import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userName: string;
  userPsd: string;
  confirmedPsd: string;
  identity: boolean;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit() {
  }
  closeWindow() {
    this.activeModal.close('Close click');
  }

}
