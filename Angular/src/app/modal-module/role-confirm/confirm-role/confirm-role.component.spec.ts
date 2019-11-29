import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmRoleComponent } from './confirm-role.component';

describe('ConfirmRoleComponent', () => {
  let component: ConfirmRoleComponent;
  let fixture: ComponentFixture<ConfirmRoleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmRoleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
