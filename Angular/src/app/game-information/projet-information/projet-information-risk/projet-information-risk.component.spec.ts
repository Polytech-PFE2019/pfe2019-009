import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetInformationRiskComponent } from './projet-information-risk.component';

describe('ProjetInformationRiskComponent', () => {
  let component: ProjetInformationRiskComponent;
  let fixture: ComponentFixture<ProjetInformationRiskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjetInformationRiskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjetInformationRiskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
