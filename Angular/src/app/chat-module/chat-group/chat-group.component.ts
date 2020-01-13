import {Component, EventEmitter, Input, OnInit, Output, OnDestroy, AfterViewChecked} from '@angular/core';
import { DialogueMessage } from '../chat-dialogue/dialogueMessage';
import { GameOnService } from 'src/app/service/gameOnService/game-on.service';
import { SocketRequest } from 'src/Request';
import { SubscriptionService } from 'src/app/service/subscriptionSerivce/subscription.service';


@Component({
  selector: 'app-chat-group',
  templateUrl: './chat-group.component.html',
  styleUrls: ['./chat-group.component.css']
})
export class ChatGroupComponent implements OnInit, OnDestroy, AfterViewChecked {
  @Input() data: any;
  @Output() closeDialogue = new EventEmitter();
  @Output() minusDialogue = new EventEmitter();
  @Output() openDialogue = new EventEmitter();
  isOpenGroupChat = true;
  messages: DialogueMessage[] = [];
  myMessage = '';
  userID: string;
  subGame: any;
  scrollHeight:any;

  constructor(private gameService: GameOnService, private subscription: SubscriptionService) {
  }

  ngAfterViewChecked(): void {
    let chatGroupe =document.getElementById("listMessages");
    if(this.scrollHeight !== chatGroupe.scrollHeight){
      this.scrollHeight = chatGroupe.scrollHeight;
      chatGroupe.scrollTop = chatGroupe.scrollHeight;
    }
  }

  ngOnInit() {
    this.userID = this.subscription.userId;
    this.gameService.groupChatMessages.forEach(data => {
      this.manageDataResponse(data);
    }
    );
    this.subGame = this.gameService.reponses$.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'MSG_GROUP_CHAT':
          this.manageDataResponse(data);
          break;
      }
    });
  }

  sendMessage() {
    const request = {
      request: 'MSG_GROUP_CHAT',
      message: this.myMessage,
      userID: this.gameService.userID,
      gameID: this.gameService.gameID,
    } as SocketRequest;

    this.gameService.messages.next(request);
    this.myMessage = '';
  }

  openGroupChat(): void {
    this.isOpenGroupChat = true;
    console.log('open groupChat');
  }

  closeGroupChat(): void {
    this.isOpenGroupChat = false;
    this.closeDialogue.emit();
  }

  ngOnDestroy() {
    this.subGame.unsubscribe();
  }

  manageDataResponse(data) {
    let isSenders = false;
    if (data.userID === this.userID) {
      isSenders = true;
    }
    const message = {
      message: data.message,
      userID: data.userID,
      isSender: isSenders,
      roleID: data.roleID
    } as DialogueMessage;
    this.messages.push(message);
  }

  minus() {
    this.minusDialogue.emit(true);
  }

  open() {
    this.openDialogue.emit(true);
  }
}
