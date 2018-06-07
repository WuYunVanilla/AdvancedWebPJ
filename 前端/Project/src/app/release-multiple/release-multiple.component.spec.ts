import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaseMultipleComponent } from './release-multiple.component';

describe('ReleaseMultipleComponent', () => {
  let component: ReleaseMultipleComponent;
  let fixture: ComponentFixture<ReleaseMultipleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReleaseMultipleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReleaseMultipleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
