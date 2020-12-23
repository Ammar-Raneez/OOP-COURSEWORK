import { TestBed } from '@angular/core/testing';

import { SelectedClubService } from './selected-club.service';

describe('SelectedClubService', () => {
  let service: SelectedClubService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SelectedClubService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
