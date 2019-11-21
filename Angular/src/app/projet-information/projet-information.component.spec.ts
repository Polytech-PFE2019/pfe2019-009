import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetInformationComponent } from './projet-information.component';

describe('ProjetInformationComponent', () => {
  let component: ProjetInformationComponent;
  let fixture: ComponentFixture<ProjetInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjetInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjetInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
