import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MultipleQuestion} from '../multiple-question';
import {ShortQuestion} from '../short-question';
import {NodeService} from '../node.service';

import '../../assets/bootstrap/js/bootstrap.js';

@Component({
    selector: 'app-homework',
    templateUrl: './homework.component.html',
    styleUrls: ['./homework.component.css']
})
export class HomeworkComponent implements OnInit, OnChanges {
    multipleQuestion: MultipleQuestion[];
    shortQuestion: ShortQuestion[];

    multiple: MultipleQuestion = new MultipleQuestion();
    short: ShortQuestion = new ShortQuestion();

    success = false;
    fail = false;

    @Input() course_id: string; // 与上层组件中course绑定
    @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input() node_id: string;

    constructor(
        private modalService: NgbModal,
        private nodeService: NodeService
    ) { }

    ngOnInit() {
        // 加载所有的选择题和简答题
        this.updateHomework();
    }

    ngOnChanges() {
        this.updateHomework();
    }


    updateHomework() {
        // 获取所有的选择题
        this.nodeService.getMultiple(
            this.course_id,
            this.mind_id,
            this.node_id).subscribe(
            value => this.setMultiple(value));

        // 获取所有的简答题
        this.nodeService.getShort(
            this.course_id,
            this.mind_id,
            this.node_id).subscribe(
            value => this.setShort(value));
    }

    setMultiple(value) {
        this.multipleQuestion = value;
    }

    setShort(value) {
        this.shortQuestion = value;
    }

    releaseMultiple () {
        // 发布选择题
        this.nodeService.releaseMutiple(
            this.course_id,
            this.mind_id,
            this.node_id,
            this.multiple)
            .subscribe((value => this.checkMultiple(value['success'])));
    }

    releaseShort() {
        // 发布简答题
        this.nodeService.releaseShort(
            this.course_id,
            this.mind_id,
            this.node_id,
            this.short)
            .subscribe((value => this.checkShort(value['success'])));
    }

    checkMultiple(value) {
        if (value) {
            this.success = true;
            // 如果发布成功则重新加载作业（以获取最新添加的作业）
            this.updateHomework();
            // 如果发布成功则新建一个选择题（清除缓存）
            this.multiple = new MultipleQuestion();
        } else {
            this.fail = true;
        }
    }

    checkShort(value) {
        if (value) {
            this.success = true;
            this.updateHomework();
            this.short = new ShortQuestion();
        } else {
            this.fail = true;
        }
    }


    changeSuccess() {
      this.success = false;
    }

    changeFail() {
      this.fail = false;
    }
}
