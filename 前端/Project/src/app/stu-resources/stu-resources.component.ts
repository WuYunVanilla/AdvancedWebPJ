import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {NodeService} from '../node.service';

@Component({
    selector: 'app-stu-resources',
    templateUrl: './stu-resources.component.html',
    styleUrls: ['./stu-resources.component.css']
})
export class StuResourcesComponent implements OnInit, OnChanges {

    @Input() course_id: string; // 与上层组件中course绑定
    @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input() node_id: string;

    material_names: string[] = [];
    link_addresses: string[];

    constructor(private nodeService: NodeService) {
        const ip = window.sessionStorage.getItem('ip');
    }

    ngOnInit() {
    }

    ngOnChanges() {

        this.updateMaterials();
        this.updateLinks();
    }

    download(file_name: string) {
        this.nodeService.requestMaterialBlob(
            this.course_id, this.mind_id, this.node_id, file_name).subscribe(r => {

            this.nodeService.downFile(r, file_name);
        });
    }

    updateMaterials() {
        this.nodeService.getMaterials(this.course_id, this.mind_id, this.node_id).subscribe(r => {
            this.material_names = r;
        });
    }

    updateLinks() {
        this.nodeService.getLinks(this.course_id, this.mind_id, this.node_id).subscribe(value => {
            this.link_addresses = value;
        });
    }
}
