import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentContractComponent } from './payment-contract.component';

describe('PaymentContractComponent', () => {
  let component: PaymentContractComponent;
  let fixture: ComponentFixture<PaymentContractComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentContractComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
