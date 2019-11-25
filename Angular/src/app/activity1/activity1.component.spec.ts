import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Activity1Component } from './activity1.component';

describe('Activity1Component', () => {
  let component: Activity1Component;
  let fixture: ComponentFixture<Activity1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Activity1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Activity1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
