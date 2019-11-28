import {Injectable, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subject, Subscription} from 'rxjs';
import {map} from 'rxjs/operators';
import {WebsocketService} from '../webSocketService/websocket.service';
import {SocketRequest} from 'src/Request';
import {URLRoom} from '../../model/url';
import {Globals} from '../../globals';
import {SubscriptionService} from '../subscriptionSerivce/subscription.service';


@Injectable()
export class LobbyService implements OnDestroy {
  roomId: number;
  username: string;
  subUserName: Subscription;

  public messages: Subject<SocketRequest>;
  userID: string;

  constructor(private wsService: WebsocketService,
              private subscription: SubscriptionService) {
    this.subUserName = this.subscription.userName$.subscribe(name => {
      this.username = name;
    });
    this.messages = <Subject<SocketRequest>>wsService
      .connect(URLRoom)
      .pipe(
        map((response: MessageEvent): SocketRequest => {
          const data = JSON.parse(response.data);
          console.log(data);
          let playerList = [];
          if (JSON.stringify(data).includes('players')) {
            playerList = data.players;
            console.log(playerList);
          }
          if (JSON.stringify(data).includes('userID')) {
            if (data.userID !== undefined) {
              console.log('send userID');
              this.subscription.sendUserID(data.userID);
            }
          }
          if (JSON.stringify(data).includes('roomID')) {
            if (data.userID !== undefined) {
              console.log('send roomID');
              this.subscription.sendRoomID(data.roomID);
            }
          }
          console.log(data);
          return data;
        }));
  }

  ngOnDestroy(): void {
    this.subUserName.unsubscribe();
  }
}
