import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuDetailsComponent } from './stu-details.component';

describe('StuDetailsComponent', () => {
  let component: StuDetailsComponent;
  let fixture: ComponentFixture<StuDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
