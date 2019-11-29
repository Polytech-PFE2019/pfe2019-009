import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskBackComponent } from './risk-back.component';

describe('RiskBackComponent', () => {
  let component: RiskBackComponent;
  let fixture: ComponentFixture<RiskBackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskBackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
