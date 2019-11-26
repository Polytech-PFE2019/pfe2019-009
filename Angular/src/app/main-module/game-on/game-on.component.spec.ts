import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameOnComponent } from './game-on.component';

describe('GameOnComponent', () => {
  let component: GameOnComponent;
  let fixture: ComponentFixture<GameOnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GameOnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameOnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
