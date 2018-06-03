import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

import { NgbTabChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { MindmapComponent } from '../mindmap/mindmap.component';

@Component({
    selector: 'app-details',
    templateUrl: './details.component.html',
    styleUrls: ['./details.component.css']
})
export class DetailsComponent implements AfterViewInit {

    info: string;

    @ViewChild(MindmapComponent)
    private mindMap: MindmapComponent;

    constructor() { }


    ngAfterViewInit() {
        // Redefine `seconds()` to get from the `CountdownTimerComponent.seconds` ...
        // but wait a tick first to avoid one-time devMode
        // unidirectional-data-flow-violation error

    }

    public beforeChange($event: NgbTabChangeEvent) {
        if ($event.nextId === 'tab-homework'
            || $event.nextId === 'tab-ppt'
            || $event.nextId === 'tab-resource') {
            // $event.preventDefault();
            this.mindMap.update_selected_knowledge_id();
            const info = this.mindMap.selected_knowledge_id;
            if (info === '') {
                $event.preventDefault();
                alert('请先选择一个知识节点');
            } else {
                this.info = info;
                // todo 通过 service 获取实际资源
            }
        }
    }


}
