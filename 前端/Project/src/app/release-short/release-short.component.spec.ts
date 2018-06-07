import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaseShortComponent } from './release-short.component';

describe('ReleaseShortComponent', () => {
  let component: ReleaseShortComponent;
  let fixture: ComponentFixture<ReleaseShortComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReleaseShortComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReleaseShortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
