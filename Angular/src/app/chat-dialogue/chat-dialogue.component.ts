import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GameOnService } from '../service/gameOnService/game-on.service';
import { DialogueMessage } from './dialogueMessage';
import { SocketRequest } from 'src/Request';
import { SubscriptionService } from "../service/subscriptionSerivce/subscription.service";

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

  constructor(private gameService: GameOnService, private subsciption: SubscriptionService) {
  }

  ngOnInit() {
    this.userID = this.subsciption.userId;
    this.gameService.messages.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'MSG_NEGOTIATE':
          let isSender = false;
          if (data.userID === this.userID) {
            isSender = true;
          }
          console.log(this.userID);
          const message = {
            message: data.message,
            userID: data.userID,
            isSender: isSender
          } as DialogueMessage;
          console.log(message);
          this.messages.push(message);
          break;
        case 'PRICE_NEGOTIATE':
          this.contractNumber = parseInt(data.amount);
          break;
      }
    });

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

  sendContract(amount) {
    (<HTMLInputElement>document.getElementById("contract")).value = "";
    const request = {
      request: 'PRICE_NEGOTIATE',
      amount: amount,
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
      negotiationID: this.negotiationID
    } as SocketRequest;

    this.gameService.messages.next(request);

  }

  sendMessage(message: string) {
    this.isChated = true;
    const request = {
      request: 'MSG_NEGOTIATE',
      message: message,
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
      negotiationID: this.negotiationID
    } as SocketRequest;

    this.gameService.messages.next(request);

    (<HTMLInputElement>document.getElementById("msg")).value = "";
  }
}
