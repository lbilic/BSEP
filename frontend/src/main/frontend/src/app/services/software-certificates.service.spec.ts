import { TestBed } from '@angular/core/testing';

import { SoftwareCertificatesService } from './software-certificates.service';

describe('SoftwareCertificatesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SoftwareCertificatesService = TestBed.get(SoftwareCertificatesService);
    expect(service).toBeTruthy();
  });
});
