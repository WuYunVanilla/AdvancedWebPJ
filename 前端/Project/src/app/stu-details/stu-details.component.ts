import {Component, OnInit, ViewChild} from '@angular/core';
import {MindmapService} from '../mindmap.service';
import {StuMindmapComponent} from '../stu-mindmap/stu-mindmap.component';

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

    @ViewChild(StuMindmapComponent)
    private stuMindComponent: StuMindmapComponent;

    constructor(private mindService: MindmapService) { }

    ngOnInit() {
        this.course_id = window.sessionStorage.getItem('course_id');

        this.selected_node_id = '';
        this.currentMind = '';


        this.mindService.getMindList(this.course_id).subscribe(mindList => {
            this.mindList = mindList['mindmap_id_list'];
            if (this.mindList.length > 0) {
                this.currentMind = this.mindList[0];
            }

        });
    }

    checkTabStatus() {
        this.stuMindComponent.update_selected_knowledge_id();
        this.selected_node_id = this.stuMindComponent.selected_node_id;
    }

}
