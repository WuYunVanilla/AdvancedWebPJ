import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {NodeService} from '../node.service';
import {ShortQuestion} from '../short-question';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {StuMultiple} from '../stu-multiple';

@Component({
    selector: 'app-stu-homework',
    templateUrl: './stu-homework.component.html',
    styleUrls: ['./stu-homework.component.css']
})
export class StuHomeworkComponent implements OnInit, OnChanges {
    stuMultiples: StuMultiple[];
    stuShorts: ShortQuestion[];

    @Input() course_id: string; // 与上层组件中course绑定
    @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
    @Input() node_id: string;

    constructor(
        private nodeService: NodeService
    ) { }

    ngOnInit() {
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
        this.stuMultiples = value;
    }

    setShort(value) {
        this.stuShorts = value;
    }

    // 提交选择题
    submitMultiple(stuMultiple: StuMultiple) {
        this.nodeService.answerMultiple(
            this.course_id,
            this.mind_id,
            this.node_id,
            stuMultiple).subscribe(
            value => this.checkSubmit(value['success']));
    }

    // 提交简答题
    submitShort(stuShort: ShortQuestion) {
        alert('提交成功！');
    }

    // 检查提交
    checkSubmit(value) {
        if (value) {
            alert('提交成功！');
        } else {
            alert('提交失败！');
        }
    }
}
