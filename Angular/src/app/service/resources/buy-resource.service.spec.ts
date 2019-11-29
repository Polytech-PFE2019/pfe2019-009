import { TestBed } from '@angular/core/testing';

import { BuyResourceService } from './buy-resource.service';

describe('BuyResourceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuyResourceService = TestBed.get(BuyResourceService);
    expect(service).toBeTruthy();
  });
});
