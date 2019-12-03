import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-chat-sender',
  templateUrl: './chat-sender.component.html',
  styleUrls: ['./chat-sender.component.css']
})
export class ChatSenderComponent implements OnInit {

  @Input() description: any = '';
  @Input() contract = 0;
  @Input() isReceiver = false;

  constructor() {
  }

  ngOnInit() {
  }

}
