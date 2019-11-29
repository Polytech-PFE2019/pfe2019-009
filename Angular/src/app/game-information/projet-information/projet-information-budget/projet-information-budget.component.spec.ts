import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetInformationBudgetComponent } from './projet-information-budget.component';

describe('ProjetInformationBudgetComponent', () => {
  let component: ProjetInformationBudgetComponent;
  let fixture: ComponentFixture<ProjetInformationBudgetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjetInformationBudgetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjetInformationBudgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
