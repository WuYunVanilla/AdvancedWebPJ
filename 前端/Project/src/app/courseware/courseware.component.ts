import {Component, Input, OnChanges, OnInit } from '@angular/core';
import {FileItem, FileUploader} from 'ng2-file-upload';
import {ParsedResponseHeaders} from 'ng2-file-upload/file-upload/file-uploader.class';
import {NodeService} from '../node.service';
import {environment} from '../../environments/environment';

@Component({
    selector: 'app-courseware',
    templateUrl: './courseware.component.html',
    styleUrls: ['./courseware.component.css']
})
export class CoursewareComponent implements OnInit, OnChanges {

    isCollapsed = false;

    baseUrl = '';

    @Input() course_id: string; // 与上层组件中course绑定
    @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input() node_id: string;

    uploader: FileUploader = new FileUploader({
        url: '',
        method: 'POST',
        isHTML5: true,
        itemAlias: 'courseware'
    });

    courseware_names: string[] = [];

    public hasBaseDropZoneOver = false;


    constructor(private nodeService: NodeService) {
        this.baseUrl = environment.apiUrl + 'upload_courseware/';
    }

    ngOnInit() {

        this.uploader.onSuccessItem = this.successItem.bind(this);
        this.uploader.onAfterAddingFile = this.afterAddingFile;
        this.uploader.onBuildItemForm = this.buildItemForm;

    }

    ngOnChanges() {
        this.uploader.options.url = this.baseUrl + this.course_id + '/' + this.mind_id + '/' + this.node_id;

        this.updateCoursewares();
    }


    public fileOverBase(e: any): void {
        this.hasBaseDropZoneOver = e;
    }

    afterAddingFile(fileItem: FileItem): any {
        fileItem.withCredentials = false;
    }

    buildItemForm(fileItem: FileItem, form: any): any {
        if (!!fileItem['realFileName']) {
            form.append('fileName', fileItem['realFileName']);
        }
    }

    successItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {

        this.updateCoursewares();
    }

    updateCoursewares() {
        this.nodeService.getCoursewares(this.course_id, this.mind_id, this.node_id).subscribe(r => {
            this.courseware_names = r;
        });
    }

    download(file_name: string) {
        this.nodeService.requestCoursewareBlob(
            this.course_id, this.mind_id, this.node_id, file_name).subscribe(r => {

            this.nodeService.downFile(r, file_name);
        });
    }


}
