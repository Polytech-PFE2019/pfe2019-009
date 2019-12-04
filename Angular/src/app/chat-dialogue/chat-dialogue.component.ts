import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GameOnService} from '../service/gameOnService/game-on.service';
import {DialogueMessage} from './dialogueMessage';
import {SocketRequest} from 'src/Request';
import {SubscriptionService} from '../service/subscriptionSerivce/subscription.service';

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
  isDialog = false;
  userID;
  contract = 0;
  negotiationID: string;

  messages: DialogueMessage[] = [];
  msg = '';

  constructor(private gameService: GameOnService, private subsciption: SubscriptionService) {
  }

  ngOnInit() {
    this.userID = this.subsciption.userId;
    this.gameService.messages.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'START_NEGOTIATE':
          this.negotiationID = data.negociationID;
          this.isOpenDialog = true;
          break;
        case 'MSG_NEGOTIATE':
          let isSenders = false;
          if (data.userID === this.userID) {
            isSenders = true;
          }
          console.log(this.userID);
          const message = {
            message: data.message,
            userID: data.userID,
            isSender: isSenders
          } as DialogueMessage;
          console.log(message);
          this.messages.push(message);
          console.log(this.messages);
          break;
        case 'PRICE_NEGOCIATE':
          console.log(data.amount);
          this.contractNumber = parseInt(data.amount, 10);
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

  sendContract() {
    const request = {
      request: 'PRICE_NEGOTIATE',
      amount: this.contract.toString(),
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
      negotiationID: this.negotiationID
    } as SocketRequest;

    console.log(request);
    this.gameService.messages.next(request);
    this.contract = 0;

  }

  sendMessage() {
    this.isChated = true;
    const request = {
      request: 'MSG_NEGOTIATE',
      message: this.msg,
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
      negotiationID: this.negotiationID
    } as SocketRequest;
    console.log(request);

    this.gameService.messages.next(request);
    this.msg = '';
  }
}
