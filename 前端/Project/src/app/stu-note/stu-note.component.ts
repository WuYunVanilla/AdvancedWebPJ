import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {NodeService} from '../node.service';
import {Note} from '../note';
import * as jsMind from '../../assets/jsmind/jsmind';

@Component({
  selector: 'app-stu-note',
  templateUrl: './stu-note.component.html',
  styleUrls: ['./stu-note.component.css']
})
export class StuNoteComponent implements OnInit, OnChanges {
  isCollapsed_file = false;
  isCollapsed_link = false;

  success = false;
  fail = false;

  publicText: string;
  privateText: string;
  otherText: string;

  showPublicText = false;
  showPrivateText = false;
  showOtherText = false;

  @Input() course_id: string; // 与上层组件中course绑定
  @Input() mind_id: string; // 与上层组件中选中的mindMap绑定
  @Input() node_id: string;


  publicNotes: Note[];
  privateNotes: Note[];
  otherNotes: Note[];

  note: Note = new Note();

  public hasBaseDropZoneOver = false;


  constructor(private nodeService: NodeService) {
  }

  ngOnInit() {
    this.getPublicNotes();
    this.getPrivateNotes();
    this.getOtherNotes();
  }

  ngOnChanges() {
    this.getPublicNotes();
    this.getPrivateNotes();
    this.getOtherNotes();
  }

  // 获取公有笔记
  getPublicNotes() {
    this.nodeService.getPublicNotes(
      window.sessionStorage.getItem('user_name'),
      this.course_id,
      this.mind_id,
      this.node_id).subscribe(
      value => this.setPublicNotes(value));
  }

  setPublicNotes(value) {
    this.publicNotes = value;
  }

  // 获取私有笔记
  getPrivateNotes() {
    this.nodeService.getPrivateNotes(
      window.sessionStorage.getItem('user_name'),
      this.course_id,
      this.mind_id,
      this.node_id).subscribe(
      value => this.setPrivateNotes(value));
  }

  setPrivateNotes(value) {
    this.privateNotes = value;
  }

  // 获取其他人的共有笔记
  getOtherNotes() {
    this.nodeService.getOtherNotes(
      window.sessionStorage.getItem('user_name'),
      this.course_id,
      this.mind_id,
      this.node_id).subscribe(
      value => this.setOtherNotes(value));
  }

  setOtherNotes(value) {
    this.otherNotes = value;
  }

  addNote() {
    this.success = false;
    this.fail = false;
    this.note.note_id = jsMind.util.uuid.newid();
    this.nodeService.addNote(
      window.sessionStorage.getItem('user_name'),
      this.course_id,
      this.mind_id,
      this.node_id,
      this.note).subscribe(
      value => this.checkNote(value['success']));
  }

  checkNote(value) {
    if (value) {
      this.getPublicNotes();
      this.getPrivateNotes();
      this.note = new Note();
      this.success = true;
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

  displayPublicText(content: string) {
    this.publicText = content;
    this.showPublicText = true;
  }

  hidePublicText() {
    this.showPublicText = false;
  }

  displayPrivateText(content: string) {
    this.privateText = content;
    this.showPrivateText = true;
  }

  hidePrivateText() {
    this.showPrivateText = false;
  }

  displayOtherText(content: string) {
    this.otherText = content;
    this.showOtherText = true;
  }

  hideOtherText() {
    this.showOtherText = false;
  }
}
