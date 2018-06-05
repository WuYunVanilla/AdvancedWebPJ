import {Component, OnInit } from '@angular/core';

import * as jsMind from './jsmind/jsmind.js';

const options = {
    container: 'jsmind_container',
    theme: 'greensea',
    editable: true
};

const mind = {
    'meta' : {
        'name': 'jsMind-demo-tree',
        'author': 'hizzgdev@163.com',
        'version': '0.2'
    },
    /* 数据格式声明 */
    'format': 'node_tree',
    /* 数据内容 */
    'data': {
        'id': 'root',
        'topic': 'jsMind',
        'children': [
            {
                'id': 'easy',
                'topic': 'Easy',
                'direction': 'left',
                'expanded': false,
                'children': [
                    {'id': 'easy1', 'topic': 'Easy to show'},
                    {'id': 'easy2', 'topic': 'Easy to edit'},
                    {'id': 'easy3', 'topic': 'Easy to store'},
                    {'id': 'easy4', 'topic': 'Easy to embed'}
                ]
            },
            {
                'id': 'open',
                'topic': 'Open Source',
                'direction': 'right',
                'expanded': true,
                'children': [
                    {'id': 'open1', 'topic': 'on GitHub'},
                    {'id': 'open2', 'topic': 'BSD License'}
                ]
            },
            {
                'id': 'powerful',
                'topic': 'Powerful',
                'direction': 'right',
                'children': [
                    {'id': 'powerful1', 'topic': 'Base on Javascript'},
                    {'id': 'powerful2', 'topic': 'Base on HTML5'},
                    {'id': 'powerful3', 'topic': 'Depends on you'}
                ]
            },
            {
                'id': 'other',
                'topic': 'test node',
                'direction': 'left',
                'children': [
                    {'id': 'other1', 'topic': 'I\'m from local variable'},
                    {'id': 'other2', 'topic': 'I can do everything'}
                ]
            }
        ]
    }
};



@Component({
    selector: 'app-mindmap',
    templateUrl: './mindmap.component.html',
    styleUrls: ['./mindmap.component.css']
})
export class MindmapComponent implements OnInit {

    // public mindMap;

    public mindMap;
    public selected_node_id: string;
    // showTip: boolean;

    // public info: number[] = [1];


    constructor() { }

    ngOnInit() {
        this.mindMap = jsMind.show(options);
        this.selected_node_id = '';
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
        const topic = '* Node_' + nodeid.substr(0, 5) + ' *';
        this.mindMap.add_node(selected_node, nodeid, topic);
    }

    add_brother(): void {
        const selected_node = this.mindMap.get_selected_node(); // as parent of new node

        if (!selected_node) {
            console.log('please select a node first.');
            return;
        }
        const nodeid = jsMind.util.uuid.newid();
        const topic = '* Node_' + nodeid.substr(0, 5) + ' *';
        this.mindMap.insert_node_after(selected_node, nodeid, topic);
    }

    private get_selected_nodeid() {
        const selected_node = this.mindMap.get_selected_node();
        if (!!selected_node) {
            return selected_node.id;
        }
    }

    change_node_color() {
        const selected_id = this.get_selected_nodeid();
        if (selected_id) {
            this.mindMap.set_node_color(selected_id, '#eee', null);
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
        const selected_node = this.mindMap.get_selected_node();
        if (!selected_node) {
            this.selected_node_id = '';
        } else {
            this.selected_node_id = selected_node.topic;
        }

    }

}
