import {Injectable, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subject, Subscription} from 'rxjs';
import {map} from 'rxjs/operators';
import {WebsocketService} from '../webSocketService/websocket.service';
import {SocketRequest} from 'src/Request';
import {URL} from '../../model/url';
import {Globals} from '../../globals';
import {SubscriptionService} from '../subscriptionSerivce/subscription.service';


@Injectable()
export class LobbyService implements OnDestroy {
  roomId: number;
  username: string;
  subUserName: Subscription;

  public messages: Subject<SocketRequest>;
  globals: Globals;
  userID: string;

  constructor(private wsService: WebsocketService,
              globals: Globals,
              private subscription: SubscriptionService) {
    this.globals = globals;
    this.subUserName = this.subscription.userName$.subscribe(name => {
      this.username = name;
    });
    this.messages = <Subject<SocketRequest>>wsService
      .connect(URL)
      .pipe(
        map((response: MessageEvent): SocketRequest => {
          const data = JSON.parse(response.data);
          let playerList = [];
          if (JSON.stringify(data).includes('players')) {
            playerList = JSON.parse(data.players);
          }
          if (JSON.stringify(data).includes('userID')) {
            console.log('send userID');
            this.subscription.sendUserID(data.userID);
          }
          if (JSON.stringify(data).includes('roomID')) {
            console.log('send roomID');
            this.subscription.sendRoomID(data.roomID);
          }
          data.players = playerList.map(player => {
            return JSON.parse(player);
          });
          console.log(data);
          return data;
        }));
  }

  ngOnDestroy(): void {
    this.subUserName.unsubscribe();
  }
}
