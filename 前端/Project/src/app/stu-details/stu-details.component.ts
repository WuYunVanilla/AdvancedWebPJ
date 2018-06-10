import {Component, OnInit, ViewChild} from '@angular/core';
import {MindmapComponent} from '../mindmap/mindmap.component';
import {MindmapService} from '../mindmap.service';

@Component({
    selector: 'app-stu-details',
    templateUrl: './stu-details.component.html',
    styleUrls: ['./stu-details.component.css']
})
export class StuDetailsComponent implements OnInit {

    course_id: string;

    mindList: string[];
    currentMind: string; // 当前思维导图的id

    selected_node_id: string;

    @ViewChild(MindmapComponent)
    private mindComponent: MindmapComponent;

    constructor(private mindService: MindmapService) { }

    ngOnInit() {
        this.course_id = window.sessionStorage.getItem('course_id');

        this.selected_node_id = '';
        this.currentMind = '';


        this.mindService.getMindList(this.course_id).subscribe(mindList => {
            console.log(mindList);
            this.mindList = mindList['mindmap_id_list'];
            if (this.mindList.length > 0) {
                this.currentMind = this.mindList[0];
                console.log('调用了updateMindMap()');
                console.log(this.currentMind);

            }

        });
    }

    checkTabStatus() {
        this.mindComponent.update_selected_knowledge_id();
        this.selected_node_id = this.mindComponent.selected_node_id;
    }

}
