import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatDialogueComponent } from './chat-dialogue.component';

describe('ChatDialogueComponent', () => {
  let component: ChatDialogueComponent;
  let fixture: ComponentFixture<ChatDialogueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChatDialogueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
