import {Component, Input, OnInit} from '@angular/core';

import * as jsMind from './jsmind/jsmind.js';
import './jsmind/jsmind.screenshot.js';
import {MindmapService} from '../mindmap.service';
import {st} from '@angular/core/src/render3';

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

    // current_mind = {
    //     'meta': {
    //         'name': 'jsMind-demo-tree',
    //         'author': 'hizzgdev@163.com',
    //         'version': '0.2'
    //     },
    //     'format': 'node_tree',
    //     /* 数据内容 */
    //     'data': {}
    // };

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

    init_str: string; // 创建新的思维导图时使用

    constructor(private mindService: MindmapService) { }

    ngOnInit() {
        this.init_str = jsMind.util.json.json2string(init_data);

        this.selected_node_id = '';


    }

    screen_shot() {
        this.mindMap.screenshot.shootDownload();
    }

    remove(): void {
        const selected_id = this.mindMap.get_selected_node();
        if (!selected_id) {
            console.log('please select a node first.');
            return;
        }
        this.mindMap.remove_node(selected_id);
    }

    add_child() {
        const selected_node = this.mindMap.get_selected_node(); // as parent of new node
        // alert(selected_node);
        if (!selected_node) {
            console.log('please select a node first.');
            return;
        }
        const nodeid = jsMind.util.uuid.newid();
        const topic = '* 新节点 *';
        this.mindMap.add_node(selected_node, nodeid, topic);
    }

    add_brother(): void {
        const selected_node = this.mindMap.get_selected_node(); // as parent of new node

        if (!selected_node) {
            console.log('please select a node first.');
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
        console.log(this.mind_id);
        console.log('调用了getMind()');
        this.mindService.getMind(this.course_id, this.mind_id).subscribe(mindStr => {

            console.log(mindStr);
            // const mindJson = jsMind.util.json.string2json(mindStr);
            this.mind.data = mindStr;

            if (!this.mindMap) {
                console.log('初始化mindMap!');
                this.mindMap = jsMind.show(options, this.mind);
            } else {
                console.log('使用已有的mindMap!');
                console.log(this.mind);
                this.mindMap.show(this.mind);
            }

        });



    }

    // create() {
    //     this.mindMap.show(options);
    //     this.save();
    // }

    save() {
        const mindJson = this.mindMap.get_data(); // 格式同 const mind

        const str = jsMind.util.json.json2string(mindJson.data); // 最终要传输的字符串

        console.log(str);

        this.mindService.saveMind(this.course_id, this.mind_id, str).subscribe(r => {
            console.log(r);
            console.log('保存结果' + r['success']);
        });
    }

}
