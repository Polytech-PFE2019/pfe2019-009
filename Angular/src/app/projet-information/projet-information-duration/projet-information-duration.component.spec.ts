import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetInformationDurationComponent } from './projet-information-duration.component';

describe('ProjetInformationDurationComponent', () => {
  let component: ProjetInformationDurationComponent;
  let fixture: ComponentFixture<ProjetInformationDurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjetInformationDurationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjetInformationDurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
