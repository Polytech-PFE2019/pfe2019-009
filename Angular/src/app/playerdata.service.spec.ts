import { TestBed } from '@angular/core/testing';

import { PlayerdataService } from './playerdata.service';

describe('PlayerdataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PlayerdataService = TestBed.get(PlayerdataService);
    expect(service).toBeTruthy();
  });
});
