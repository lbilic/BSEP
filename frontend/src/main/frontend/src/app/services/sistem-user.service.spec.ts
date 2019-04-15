import { TestBed } from '@angular/core/testing';

import { SistemUserService } from './sistem-user.service';

describe('SistemUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SistemUserService = TestBed.get(SistemUserService);
    expect(service).toBeTruthy();
  });
});
