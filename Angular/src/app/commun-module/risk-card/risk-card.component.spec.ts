import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskCardComponent } from './risk-card.component';

describe('RiskCardComponent', () => {
  let component: RiskCardComponent;
  let fixture: ComponentFixture<RiskCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
