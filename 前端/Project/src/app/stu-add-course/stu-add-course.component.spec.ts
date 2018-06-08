import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuAddCourseComponent } from './stu-add-course.component';

describe('StuAddCourseComponent', () => {
  let component: StuAddCourseComponent;
  let fixture: ComponentFixture<StuAddCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuAddCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuAddCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
