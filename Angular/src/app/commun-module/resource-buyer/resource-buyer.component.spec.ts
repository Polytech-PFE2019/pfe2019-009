import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceBuyerComponent } from './resource-buyer.component';

describe('ResourceBuyerComponent', () => {
  let component: ResourceBuyerComponent;
  let fixture: ComponentFixture<ResourceBuyerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResourceBuyerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourceBuyerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
