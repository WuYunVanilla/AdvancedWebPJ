import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuNoteComponent } from './stu-note.component';

describe('StuNoteComponent', () => {
  let component: StuNoteComponent;
  let fixture: ComponentFixture<StuNoteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuNoteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
