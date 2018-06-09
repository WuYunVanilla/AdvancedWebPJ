import { Component, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';

@Component({
    selector: 'app-resources',
    templateUrl: './resources.component.html',
    styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {

    uploader: FileUploader = new FileUploader({
        url: 'localhost:80/uploadFile',
        method: 'POST',
        isHTML5: true
    });

    material_names: string[] = ['1.txt', '2.txt'];
    links: string[] = ['github.com', 'www.w3cschool.com'];


    public hasBaseDropZoneOver = false;


    constructor() { }

    ngOnInit() {
    }


    public fileOverBase(e: any): void {
        this.hasBaseDropZoneOver = e;
    }



}
