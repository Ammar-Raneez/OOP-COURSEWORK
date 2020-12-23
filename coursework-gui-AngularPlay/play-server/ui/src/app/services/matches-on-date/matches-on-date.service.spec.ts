import { TestBed } from '@angular/core/testing';

import { MatchesOnDateService } from './matches-on-date.service';

describe('MatchesOnDateService', () => {
  let service: MatchesOnDateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchesOnDateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
