import { Component, OnInit, ViewChild } from '@angular/core';

import * as jsMind from '../../assets/jsmind/jsmind.js';
import { MindmapComponent } from '../mindmap/mindmap.component';
import { MindmapService } from '../mindmap.service';
import {HomeworkComponent} from '../homework/homework.component';
import {Mind} from '../mind';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: 'app-details',
    templateUrl: './details.component.html',
    styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

    modalRef = null; // 创建新思维导图时打开的框
    newMindJson = {
        'meta' : {
            'name': '',
            'author': 'user',
            'version': '0.2'
        },
        /* 数据格式声明 */
        'format': 'node_tree',
        /* 数据内容 */

        'data': {
            'id': 'root',
            'topic': '* 根节点 *',
            'expanded': true
        }
    }; // 用作创建新思维导图时填充
    new_mind_name = ''; // 创建新的思维导图所输入的名字

    course_id: string; // 课程id

    mindList: Mind[] = []; // 思维导图列表
    currentMind: Mind = null; // 当前思维导图的对象

    selected_node_id: string; // 当前选中的节点的id


    @ViewChild(MindmapComponent)
    private mindComponent: MindmapComponent;

    @ViewChild(HomeworkComponent)
    private homeworkComponent: HomeworkComponent;

    constructor(private mindService: MindmapService, private modalService: NgbModal) { }

    ngOnInit() {
        this.course_id = window.sessionStorage.getItem('course_id');

        this.selected_node_id = '';

        this.mindService.getMindList(this.course_id).subscribe(list => {

            this.mindList = Array.from(list);
            if (this.mindList.length > 0) {
                this.currentMind = this.mindList[0];
            }

        });
    }

    checkTabStatus() {
        this.mindComponent.update_selected_knowledge_id();
        this.selected_node_id = this.mindComponent.selected_node_id;
    }

    // 打开创建思维导图的框
    open(content) {

        this.modalRef = this.modalService.open(content);
        this.modalRef.result.then((result) => {
            alert(result);
            // content.close();
        });
    }



    createMind() {
        for (const m of this.mindList) {
            if (m.name === this.new_mind_name) {
                alert('已经存在同名的思维导图');
                return;
            }
        }

        // 参考jsmind.js里面的方法确定新的思维导图的id
        const new_mind_id = (new Date().getTime().toString(16)
            + Math.random().toString(16).substr(2)).substr(2, 16);
        this.newMindJson.meta.name = this.new_mind_name;

        this.mindService.createMind(
            this.course_id,
            new_mind_id,
            jsMind.util.json.json2string(this.newMindJson)
        ).subscribe(r => {
            if (r['success']) {
                const new_mind = {'id': new_mind_id, 'name': this.new_mind_name};

                this.mindList.push(new_mind);
                this.currentMind = new_mind;

                this.new_mind_name = '';

                this.modalRef.close('创建成功');


            }
        });


    }

}
