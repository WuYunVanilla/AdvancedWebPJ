import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

import { NgbTabChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { MindmapComponent } from '../mindmap/mindmap.component';

@Component({
    selector: 'app-details',
    templateUrl: './details.component.html',
    styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

    selected_node_id: string;

    @ViewChild(MindmapComponent)
    private mindMap: MindmapComponent;

    constructor() { }

    ngOnInit() {
        this.selected_node_id = '';
    }

    checkTabStatus() {
        this.mindMap.update_selected_knowledge_id();
        this.selected_node_id = this.mindMap.selected_node_id;
    }


}
