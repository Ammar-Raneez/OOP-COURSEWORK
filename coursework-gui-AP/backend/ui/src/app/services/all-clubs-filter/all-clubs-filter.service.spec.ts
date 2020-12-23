import { TestBed } from '@angular/core/testing';

import { AllClubsFilterService } from './all-clubs-filter.service';

describe('AllMatchesFilterService', () => {
  let service: AllClubsFilterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllClubsFilterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
