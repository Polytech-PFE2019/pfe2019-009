import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DialogueMessage} from '../chat-dialogue/dialogueMessage';

@Component({
  selector: 'app-chat-group',
  templateUrl: './chat-group.component.html',
  styleUrls: ['./chat-group.component.css']
})
export class ChatGroupComponent implements OnInit {
  @Input() data: any;
  @Output() closeDialogue = new EventEmitter;
  isOpenGroupChat = true;
  messages: DialogueMessage[] = [];

  constructor() {
  }

  ngOnInit() {
  }

  sendMessage(message) {

  }

  openGroupChat(): void {
    this.isOpenGroupChat = true;
    console.log('open groupChat')
  }

  closeGroupChat(): void {
    this.isOpenGroupChat = false;
    this.closeDialogue.emit();
  }

}
