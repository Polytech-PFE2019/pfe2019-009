import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-chat-dialogue',
  templateUrl: './chat-dialogue.component.html',
  styleUrls: ['./chat-dialogue.component.css']
})
export class ChatDialogueComponent implements OnInit {
  @Input() receiver = {
    class: 'receiver',
    contract: 20,
    description: 40,
  };
  @Input() isOpenDialog = false;
  @Output() sendCloseDialog = new EventEmitter();
  value = 100;
  contractNumber = 0;
  inputValue: string;
  isChated = false;
  listOfSend: any[] = [];
  isDialog = false;

  constructor() {
  }

  ngOnInit() {
    this.listOfSend.push(this.receiver);
  }

  closeChat() {
    this.isOpenDialog = false;
    this.sendCloseDialog.emit(false);
  }

  send() {
    this.isChated = true;
    const i = {
      class: 'sender',
      contract: this.value,
      description: this.inputValue,
    };
    this.listOfSend.push(i);
    console.log(this.listOfSend);
  }

  testAddReceiver() {
    this.listOfSend.push(this.receiver);
  }

  senContract() {
    this.contractNumber = this.value;
  }

  sendDescription() {
    this.isChated = true;
    const i = {
      class: 'sender',
      isSend: true,
      contract: this.value,
      description: this.inputValue,
    };
    this.listOfSend.push(i);
    console.log(this.listOfSend);
  }
}
