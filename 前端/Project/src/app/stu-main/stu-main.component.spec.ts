import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuMainComponent } from './stu-main.component';

describe('StuMainComponent', () => {
  let component: StuMainComponent;
  let fixture: ComponentFixture<StuMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
