import {Component, Input, OnInit} from '@angular/core';
import {FileItem, FileUploader} from 'ng2-file-upload';
import {ParsedResponseHeaders} from 'ng2-file-upload/file-upload/file-uploader.class';

@Component({
    selector: 'app-courseware',
    templateUrl: './courseware.component.html',
    styleUrls: ['./courseware.component.css']
})
export class CoursewareComponent implements OnInit {

    baseUrl = 'http://10.222.129.245:8081/upload_material/';

    @Input() course_id: string; // 与上层组件中course绑定
    @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input() node_id: string;

    uploader: FileUploader = new FileUploader({
        url: '',
        method: 'POST',
        isHTML5: true,
        itemAlias: 'material'
    });



    courseware_names: string[] = ['1.pdf', '2.pdf'];


    public hasBaseDropZoneOver = false;


    constructor() { }

    ngOnInit() {

        this.uploader.onAfterAddingFile = this.afterAddingFile;
        this.uploader.onBuildItemForm = this.buildItemForm;
        this.uploader.onSuccessItem = this.successItem;
    }

    ngOnChanges() {
        this.uploader.options.url = this.baseUrl + this.course_id + '/' + this.mind_id + '/' + this.node_id;


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

    successItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
        console.log('上传成功，response为' + response);
    }

}
