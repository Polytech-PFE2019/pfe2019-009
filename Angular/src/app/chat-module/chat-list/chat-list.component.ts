import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.css']
})
export class ChatListComponent implements OnInit {
  @Input() isChat = false;
  @Output() close = new EventEmitter();
  isDialog = false;
  title = '';
  data = [
    {
      title: 'Ant Design Title 1'
    },
    {
      title: 'Ant Design Title 2'
    },
    {
      title: 'Ant Design Title 3'
    },
    {
      title: 'Ant Design Title 4'
    }
  ];

  constructor() {
  }

  ngOnInit() {
  }

  closeChat() {
    this.isChat = false;
    this.close.emit(false);
  }

  getChat(t) {
    console.log('getChat');
    this.title = t;
    this.isDialog = true;
  }

  ifCloseDialog($event: any) {
    this.isDialog = $event;
  }
}
