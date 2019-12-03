import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GameOnService } from '../service/gameOnService/game-on.service';
import { DialogueMessage } from './dialogueMessage';
import { SocketRequest } from 'src/Request';

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
  @Input() negotiationID: string;
  @Output() sendCloseDialog = new EventEmitter();
  value = 100;
  contractNumber = 0;
  inputValue: string;
  isChated = false;
  isDialog = false;
  userID;

  messages: DialogueMessage[] = []

  constructor(private gameService: GameOnService) {
  }

  ngOnInit() {
    this.userID = this.gameService.userID;
    this.gameService.messages.subscribe(data => {
      console.log(data)
      switch (data.response) {
        case "MSG_NEGOTIATE":
          let isSender = false;
          if (data.userID === this.userID) {
            isSender = true;
          }
          console.log(this.userID);
          const message = {
            message: data.message,
            userID: data.userID,
            isSender: isSender
          } as DialogueMessage
          console.log(message)
          this.messages.push(message)
      }
    })

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
  }

  testAddReceiver() {
  }

  sendContract() {
    this.contractNumber = this.value;
  }

  sendMessage(message: string) {
    this.isChated = true;
    const request = {
      request: 'MSG_NEGOTIATE',
      message: message,
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
      negotiationID: message
    } as SocketRequest;

    this.gameService.messages.next(request)
  }
}
