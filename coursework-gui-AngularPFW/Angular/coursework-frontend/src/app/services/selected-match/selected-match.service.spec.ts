import { TestBed } from '@angular/core/testing';

import { SelectedMatchService } from './selected-match.service';

describe('SelectedMatchService', () => {
  let service: SelectedMatchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SelectedMatchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
