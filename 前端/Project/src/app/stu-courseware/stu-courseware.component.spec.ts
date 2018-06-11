import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuCoursewareComponent } from './stu-courseware.component';

describe('StuCoursewareComponent', () => {
  let component: StuCoursewareComponent;
  let fixture: ComponentFixture<StuCoursewareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuCoursewareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuCoursewareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
