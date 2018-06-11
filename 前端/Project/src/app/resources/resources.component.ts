import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {FileItem, FileUploader} from 'ng2-file-upload';
import {ParsedResponseHeaders} from 'ng2-file-upload/file-upload/file-uploader.class';
import {NodeService} from '../node.service';

@Component({
    selector: 'app-resources',
    templateUrl: './resources.component.html',
    styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit, OnChanges {

    baseUrl = '';

    @Input() course_id: string; // 与上层组件中course绑定
    @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input() node_id: string;

    uploader: FileUploader = new FileUploader({
        url: '',
        method: 'POST',
        isHTML5: true,
        itemAlias: 'material'
    });



    material_names: string[] = [];
    link_addresses: string[];
    link_address: string;


    public hasBaseDropZoneOver = false;


    constructor(private nodeService: NodeService) {
        const ip = window.sessionStorage.getItem('ip');
        this.baseUrl = 'http://' + ip + ':8081/upload_material/';
    }

    ngOnInit() {

        this.uploader.onSuccessItem = this.successItem.bind(this);
        this.uploader.onAfterAddingFile = this.afterAddingFile;
        this.uploader.onBuildItemForm = this.buildItemForm;
        // this.uploader.onSuccessItem = this.successItem;
        console.log('course_id=' + this.course_id);
        console.log('mind_id=' + this.mind_id);
        console.log('node_id=' + this.node_id);

        this.getLinkAddrs();
    }

    ngOnChanges() {
        this.uploader.options.url = this.baseUrl + this.course_id + '/' + this.mind_id + '/' + this.node_id;

        this.updateResources();
        this.getLinkAddrs();
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

        this.updateResources();
    }

    updateResources() {
        this.nodeService.getResourses(this.course_id, this.mind_id, this.node_id).subscribe(r => {
            this.material_names = r;
            console.log(r);
        });
    }

    download(file_name: string) {
      this.nodeService.requestBlob(
        this.course_id, this.mind_id, this.node_id, 'bull.png').subscribe(r => {

        this.nodeService.downFile(r, file_name, 'application/json');
      });
    }

    getLinkAddrs() {
      this.nodeService.getLinkResourses(
        this.course_id,
        this.mind_id,
        this.node_id).subscribe(
        value => this.setLinkAddrs(value));
    }

    setLinkAddrs(value) {
      this.link_addresses = value;
    }

    uploadLink() {
      this.nodeService.upload_link(
        this.course_id,
        this.mind_id,
        this.node_id,
        this.link_address).subscribe(
        value => this.checkLink(value['success']));
    }

    checkLink(value) {
      if (value) {
        alert('上传成功！');
        this.getLinkAddrs();
        this.link_address = '';
      } else {
        alert('上传失败！');
      }
    }
}
