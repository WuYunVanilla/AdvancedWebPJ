import { TestBed, inject } from '@angular/core/testing';

import { MindmapService } from './mindmap.service';

describe('MindmapService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MindmapService]
    });
  });

  it('should be created', inject([MindmapService], (service: MindmapService) => {
    expect(service).toBeTruthy();
  }));
});
