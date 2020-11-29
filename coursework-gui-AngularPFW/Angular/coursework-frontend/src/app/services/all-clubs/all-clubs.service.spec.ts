import { TestBed } from '@angular/core/testing';

import { AllClubsService } from './all-clubs.service';

describe('PointsTableService', () => {
  let service: AllClubsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllClubsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
