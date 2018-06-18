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
    selector: 'app-mindmap',
    templateUrl: './mindmap.component.html',
    styleUrls: ['./mindmap.component.css']
})
export class MindmapComponent implements OnInit {

    isCollapsed = false;

    nodes_info = [];

    success = false;
    fail = false;

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
        if (this.mind_id) {
            this.updateMindMap();
        }

    }

    mind = {
        'meta' : {
            'name': '',
            'author': 'user',
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

    init_str = '';

    ngOnInit() {

        this.selected_node_id = '';
        this.init_str = jsMind.util.json.json2string(init_data);
    }

    screen_shot() {
        this.mindMap.screenshot.shootDownload();
    }

    remove(): void {
        const selected_id = this.mindMap.get_selected_node();
        if (!selected_id) {
            return;
        }
        this.mindMap.remove_node(selected_id);
    }

    add_child() {
        const selected_node = this.mindMap.get_selected_node(); // as parent of new node
        if (!selected_node) {
            return;
        }
        const nodeid = jsMind.util.uuid.newid();
        const topic = '* 新节点 *';
        this.mindMap.add_node(selected_node, nodeid, topic);
    }

    add_brother(): void {
        const selected_node = this.mindMap.get_selected_node(); // as parent of new node

        if (!selected_node) {
            return;
        }
        const nodeid = jsMind.util.uuid.newid();
        const topic = '* 新节点 *';
        this.mindMap.insert_node_after(selected_node, nodeid, topic);
    }

    private get_selected_nodeid() {
        const selected_node = this.mindMap.get_selected_node();
        if (!!selected_node) {
            return selected_node.id;
        }
    }

    change_node_color(color: string) {
        const selected_id = this.get_selected_nodeid();
        if (selected_id) {
            this.mindMap.set_node_color(selected_id, color, null);
        }

    }

    change_font_color(color: string) {
        const selected_id = this.get_selected_nodeid();
        const sel = this.mindMap.get_selected_node();
        if (selected_id) {
            this.mindMap.set_node_color(selected_id, null, color);
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


            // this.mind.data = mindStr;
            this.mind = mindStr;

            if (!this.mindMap) {
                this.mindMap = jsMind.show(options, this.mind);
            } else {
                this.mindMap.show(this.mind);
            }

        });

        this.mindService.getAccuracy(this.course_id, this.mind_id).subscribe(list => {
            this.nodes_info = list;
        });

    }

    save() {
        const mindJson = this.mindMap.get_data(); // 格式同 const mind

        // const str = jsMind.util.json.json2string(mindJson.data); // 最终要传输的字符串
        const str = jsMind.util.json.json2string(mindJson); // 最终要传输的字符串

        this.success = false;
        this.fail = false;

        this.mindService.saveMind(this.course_id, this.mind_id, str).subscribe(r => {
            if (r['success']) {
              this.success = true;
            } else {
              this.fail = true;
            }
        });
    }

    changeSuccess() {
      this.success = false;
    }

    changeFail() {
      this.fail = false;
    }

}
