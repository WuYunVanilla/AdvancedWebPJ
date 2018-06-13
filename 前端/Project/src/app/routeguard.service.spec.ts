import { TestBed, inject } from '@angular/core/testing';

import { RouteguardService } from './routeguard.service';

describe('RouteguardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RouteguardService]
    });
  });

  it('should be created', inject([RouteguardService], (service: RouteguardService) => {
    expect(service).toBeTruthy();
  }));
});
