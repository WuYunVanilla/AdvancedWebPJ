import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  showMaps = false;
  imgUrl = 'http://localhost:63342/Project/src/app/img/more.png';
  constructor() { }

  ngOnInit() {
  }
  changeShowMaps() {
    this.showMaps = !this.showMaps;
    if (this.showMaps) {
      this.imgUrl = 'http://localhost:63342/Project/src/app/img/up.png';
    } else {
      this.imgUrl = 'http://localhost:63342/Project/src/app/img/more.png';
    }
  }
}
