import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMindmapComponent } from './create-mindmap.component';

describe('CreateMindmapComponent', () => {
  let component: CreateMindmapComponent;
  let fixture: ComponentFixture<CreateMindmapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMindmapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMindmapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
