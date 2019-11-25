import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyResourcesComponent } from './buy-resources.component';

describe('BuyResourcesComponent', () => {
  let component: BuyResourcesComponent;
  let fixture: ComponentFixture<BuyResourcesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuyResourcesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
