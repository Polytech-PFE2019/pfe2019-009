import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskContainerComponent } from './risk-container.component';

describe('RiskContainerComponent', () => {
  let component: RiskContainerComponent;
  let fixture: ComponentFixture<RiskContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
