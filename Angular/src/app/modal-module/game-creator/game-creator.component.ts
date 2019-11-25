import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {SocketRequest} from '../../../Request';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {HttpParams} from '@angular/common/http';
import {Subscription} from 'rxjs';
import {Globals} from "../../globals";

@Component({
  selector: 'app-game-creator',
  templateUrl: './game-creator.component.html',
  styleUrls: ['./game-creator.component.css']
})
export class GameCreatorComponent implements OnInit {
  @Input() userName = '';
  isCreatingSalon: any;
  @Output() test = new EventEmitter();
  subRoomId: Subscription;
  roomID: string;


  constructor(private route: ActivatedRoute,
              private router: Router,
              private globals: Globals,
              private subscriptionService: SubscriptionService,
              private lobbyService: LobbyService) {
    lobbyService.messages.subscribe(msg => {
      console.log('Response: ' + msg.response);

      // Implement here onMessage methods

      switch (msg.response) {
        case 'UPDATE':
          this.updateRoom();
          break;
      }
    });
  }

  ngOnInit() {
    this.subRoomId = this.subscriptionService.roomID$.subscribe(data => {
      console.log(data);
      this.roomID = data;
    });
  }

  updateRoom() {

  }

  handleCancel() {
    this.isCreatingSalon = false;
  }

  handleOk() {
    this.test.emit(1);
    this.sendRequest();
    console.log(this.roomID);
    this.router.navigate(['gameroom']);
    this.isCreatingSalon = false;
  }

  sendRequest() {
    const message = {
      request: 'CREATE_GAME',
      username: this.userName
    };
    this.lobbyService.messages.next(message as SocketRequest);
  }
}
