import { Component, OnInit, ViewChild } from '@angular/core';

import { MindmapComponent } from '../mindmap/mindmap.component';
import {MindmapService} from '../mindmap.service';



@Component({
    selector: 'app-details',
    templateUrl: './details.component.html',
    styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

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


    createMind() {
        // 参考jsmind.js里面的方法确定新的思维导图的id
        const new_mind_id = (new Date().getTime().toString(16)
            + Math.random().toString(16).substr(2)).substr(2, 16);


        this.mindService.createMind(this.course_id, new_mind_id, this.mindComponent.init_str).subscribe(r => {
            if (r['success']) {
                console.log('思维导图创建成功!');

                this.mindList.push(new_mind_id);
                this.currentMind = new_mind_id;

                // 调用子组件的更新方法来更新视图
                console.log('调用了updateMindMap() in detail line 65');
                // this.mindComponent.updateMindMap();
            } else {
                console.error('思维导图创建失败!');
            }

        });


    }

}
