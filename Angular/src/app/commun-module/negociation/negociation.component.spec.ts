import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NegociationComponent } from './negociation.component';

describe('NegociationComponent', () => {
  let component: NegociationComponent;
  let fixture: ComponentFixture<NegociationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NegociationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NegociationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
