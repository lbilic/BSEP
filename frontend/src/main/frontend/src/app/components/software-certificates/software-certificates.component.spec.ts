import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SoftwareCertificatesComponent } from './software-certificates.component';

describe('SoftwareCertificatesComponent', () => {
  let component: SoftwareCertificatesComponent;
  let fixture: ComponentFixture<SoftwareCertificatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SoftwareCertificatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SoftwareCertificatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
