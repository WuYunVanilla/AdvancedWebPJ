import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuResourcesComponent } from './stu-resources.component';

describe('StuResourcesComponent', () => {
  let component: StuResourcesComponent;
  let fixture: ComponentFixture<StuResourcesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuResourcesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
