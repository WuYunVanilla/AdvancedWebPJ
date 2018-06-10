import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {FileItem, FileUploader} from 'ng2-file-upload';

@Component({
    selector: 'app-resources',
    templateUrl: './resources.component.html',
    styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit, OnChanges {

    // baseUrl = 'http://192.168.1.124:8081/upload_material/';

    @Input() course_id: string; // 与上层组件中course绑定
    @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input() node_id: string;

    uploader: FileUploader = new FileUploader({
        url: '',
        method: 'POST',
        isHTML5: true
    });



    material_names: string[] = ['1.txt', '2.txt'];
    links: string[] = ['github.com', 'www.w3cschool.com'];


    public hasBaseDropZoneOver = false;


    constructor() { }

    ngOnInit() {

        this.uploader.onAfterAddingFile = this.afterAddingFile;

        this.uploader.onBuildItemForm = this.buildItemForm;
    }

    ngOnChanges() {
        // this.uploader.options.url = this.baseUrl + this.course_id + '/' + this.mind_id + '/' + this.node_id;
        this.uploader.options.url = 'http://localhost:80/upload.php';
    }


    public fileOverBase(e: any): void {
        this.hasBaseDropZoneOver = e;
    }

    afterAddingFile(fileItem: FileItem): any {
        fileItem.withCredentials = false;
        console.log(fileItem);
    }

    buildItemForm(fileItem: FileItem, form: any): any {
        if (!!fileItem['realFileName']) {
            console.log(form);
            form.append('fileName', fileItem['realFileName']);
        }
    }




}
