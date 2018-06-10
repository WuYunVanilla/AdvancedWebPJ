import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StuMindmapComponent } from './stu-mindmap.component';

describe('StuMindmapComponent', () => {
  let component: StuMindmapComponent;
  let fixture: ComponentFixture<StuMindmapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StuMindmapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StuMindmapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
