import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuHomeworkComponent } from './stu-homework.component';

describe('StuHomeworkComponent', () => {
  let component: StuHomeworkComponent;
  let fixture: ComponentFixture<StuHomeworkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuHomeworkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuHomeworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
