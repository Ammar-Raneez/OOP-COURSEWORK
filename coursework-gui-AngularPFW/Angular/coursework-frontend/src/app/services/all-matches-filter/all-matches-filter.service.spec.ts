import { TestBed } from '@angular/core/testing';

import { AllMatchesFilterService } from './all-matches-filter.service';

describe('AllMatchesFilterService', () => {
  let service: AllMatchesFilterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllMatchesFilterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
