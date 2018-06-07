import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
    showMaps = false;
    imgUrl = 'assets/img/more.png';
    constructor() { }

    ngOnInit() {
    }

    changeShowMaps() {
        this.showMaps = !this.showMaps;
        if (this.showMaps) {
            this.imgUrl = 'assets/img/up.png';
        } else {
            this.imgUrl = 'assets/img/more.png';
        }
    }
}
