import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityDisplayerComponent } from './activity-displayer.component';

describe('ActivityDisplayerComponent', () => {
  let component: ActivityDisplayerComponent;
  let fixture: ComponentFixture<ActivityDisplayerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityDisplayerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityDisplayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
