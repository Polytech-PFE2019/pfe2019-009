import { TestBed } from '@angular/core/testing';

import { GameOnService } from './game-on.service';

describe('GameOnService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GameOnService = TestBed.get(GameOnService);
    expect(service).toBeTruthy();
  });
});
