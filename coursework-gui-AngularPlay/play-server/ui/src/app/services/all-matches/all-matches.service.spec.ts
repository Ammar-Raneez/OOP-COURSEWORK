import { TestBed } from '@angular/core/testing';

import { AllMatchesService } from './all-matches.service';

describe('AllMatchesService', () => {
  let service: AllMatchesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllMatchesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
