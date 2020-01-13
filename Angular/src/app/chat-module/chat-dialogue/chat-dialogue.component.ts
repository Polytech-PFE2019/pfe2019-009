import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {DialogueMessage} from './dialogueMessage';
import {SocketRequest} from 'src/Request';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {Subscription} from 'rxjs';
import {NzConfigService, NzNotificationService} from 'ng-zorro-antd';
import {Roles} from "../../model/roles";

@Component({
  selector: 'app-chat-dialogue',
  templateUrl: './chat-dialogue.component.html',
  styleUrls: ['./chat-dialogue.component.css']
})
export class ChatDialogueComponent implements OnInit, OnDestroy {
  @Output() sendCloseDialog = new EventEmitter();
  @Output() minusDialogue = new EventEmitter();
  @Output() openDialogue = new EventEmitter();
  @Input() data: any;
  @Input() receiver = {
    class: 'receiver',
    contract: 20,
    description: 40,
  };
  @Input() title = '';
  @ViewChild('template', {static: true}) template: TemplateRef<{}>;
  value = 100;
  isOpenDialog = true;
  contractNumber = 0;
  inputValue: string;
  isChated = false;
  isDialog = false;
  userID;
  contract = 0;
  negotiationID: string;
  listOfNegociation: any[] = [];
  subGame: Subscription;
  roles = Roles;

  messages: DialogueMessage[] = [];
  msg = '';
  isProposer = true;
  isProposed = false;
  accepteStyle = {
    backgroundColor: '#c54e3c'
  };
  contractProposed = 0;
  myMessage = '';
  isMenus = false;
  myRole: any;
  withRole: any;


  constructor(private gameService: GameOnService,
              private notification: NzNotificationService,
              private nzConfigService: NzConfigService,
              private subsciption: SubscriptionService) {
  }

  ngOnInit() {
    this.myRole = this.subsciption.myRole;
    this.userID = this.subsciption.userId;
    console.log(this.data);
    if (this.data.response === 'START_NEGOTIATE') {
      console.log(this.data);
      this.negotiationID = this.data.negociationID;
    }
    if (this.data.giverID === this.myRole.id) {
      this.withRole = this.getRolebyId(this.data.receiverID);
    } else {
      this.withRole = this.getRolebyId(this.data.giverID);
    }
    this.subGame = this.gameService.reponses$.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'MSG_NEGOTIATE':
          if (data.negociationID === this.negotiationID) {
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
          }
          break;
        case 'PRICE_NEGOCIATE':
          if (data.negociationID === this.negotiationID) {
            console.log(data.amount);
            this.isProposer = this.userID === data.userID;
            this.contractNumber = parseInt(data.amount, 10);
          }
          break;
        case 'FAIL_NEGOTIATE':

          if (data.negociationID === this.negotiationID) {
            this.isOpenDialog = false;
            this.notification.blank('La négociation a échoué',
              'La négociation a échoué ! Un contrat de montant ' + data.amount + 'k a été tiré au sort',
              {nzDuration: 10});
          }
          break;
        case 'END_NEGOTIATE':
          if (data.negociationID === this.negotiationID) {
            this.isOpenDialog = false;
            // this.notification.template(this.template);

          }
          break;
      }
    });

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
      amount: this.contractProposed.toString(),
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
      negotiationID: this.negotiationID
    } as SocketRequest;
    console.log(request);
    this.contractNumber = this.contractProposed;
    // this.contractProposed = 0;
    this.gameService.messages.next(request);

  }

  sendMessage() {
    this.isChated = true;
    const request = {
      request: 'MSG_NEGOTIATE',
      message: this.myMessage,
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
      negotiationID: this.negotiationID
    } as SocketRequest;
    console.log(request);

    this.gameService.messages.next(request);
    this.myMessage = '';
  }

  ngOnDestroy(): void {
    this.subGame.unsubscribe();
  }

  openChat() {
    this.isOpenDialog = true;
  }

  getRolebyId(id) {
    return this.roles.find(next => next.id === id);
  }

  accepteContract() {
    this.accepteStyle.backgroundColor = '#4E8014';
    const req = {
      request: 'END_NEGOTIATE',
      gameID: this.gameService.gameID,
      negotiationID: this.negotiationID,
      amount: this.contractNumber.toString()
    };
    console.log(req);
    this.gameService.messages.next(req as SocketRequest);
  }

  minus() {
    // this.minusDialogue.emit(true);
    this.isMenus = true;
  }

  open() {
    // this.openDialogue.emit(true);
    this.isMenus = false;
  }

}
