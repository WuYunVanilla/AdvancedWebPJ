import {Component, Input, OnInit} from '@angular/core';

import * as jsMind from '../../assets/jsmind/jsmind.js';
import '../../assets/jsmind/jsmind.screenshot.js';
import {MindmapService} from '../mindmap.service';

const options = {
    container: 'jsmind_container',
    theme: 'greensea',
    editable: true
};

const init_data = {'id': 'root', 'topic': '* 根节点 *', 'expanded': true};


@Component({
    selector: 'app-stu-mindmap',
    templateUrl: './stu-mindmap.component.html',
    styleUrls: ['./stu-mindmap.component.css']
})
export class StuMindmapComponent implements OnInit {

    course_id = ''; // 与上层组件中course绑定
    @Input()
    set courseId(course_id: string) {
        this.course_id = course_id;
    }

    mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input()
    set mindId(mind_id: string) {
        window.sessionStorage.setItem('mindmap_id', mind_id);

        this.mind_id = mind_id;
        if (this.mind_id !== '') {
            this.updateMindMap();
        }

    }

    mind = {
        'meta' : {
            'name': 'Advanced-Web-PJ-jsMind-root',
            'author': 'nhua15@fudan.edu.cn',
            'version': '0.2'
        },
        /* 数据格式声明 */
        'format': 'node_tree',
        /* 数据内容 */

        'data': null
    };

    public mindMap; // 思维导图组件
    public selected_node_id: string; // 当前思维导图中被选中的节点

    constructor(private mindService: MindmapService) { }

    ngOnInit() {
        this.selected_node_id = '';
    }

    screen_shot() {
        this.mindMap.screenshot.shootDownload();
    }

    private get_selected_nodeid() {
        const selected_node = this.mindMap.get_selected_node();
        if (!!selected_node) {
            return selected_node.id;
        }
    }

    update_selected_knowledge_id(): void {


        if (!this.mindMap) {
            return;
        }

        const selected_node = this.mindMap.get_selected_node();
        if (!selected_node) {
            this.selected_node_id = '';
        } else {
            this.selected_node_id = selected_node.id;
        }

        window.sessionStorage.setItem('node_id', this.selected_node_id);
    }

    // 显示新的mindMap
    updateMindMap() {
        this.mindService.getMind(this.course_id, this.mind_id).subscribe(mindStr => {

            // const mindJson = jsMind.util.json.string2json(mindStr);
            this.mind.data = mindStr;

            if (!this.mindMap) {
                this.mindMap = jsMind.show(options, this.mind);
                this.mindMap.disable_edit();
            } else {
                this.mindMap.show(this.mind);
            }

        });


    }

}
