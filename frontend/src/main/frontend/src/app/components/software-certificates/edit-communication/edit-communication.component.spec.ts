import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCommunicationComponent } from './edit-communication.component';

describe('EditCommunicationComponent', () => {
  let component: EditCommunicationComponent;
  let fixture: ComponentFixture<EditCommunicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCommunicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCommunicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
