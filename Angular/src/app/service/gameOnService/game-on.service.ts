import { Injectable } from '@angular/core';
import {WebsocketService} from '../webSocketService/websocket.service';
import {SubscriptionService} from '../subscriptionSerivce/subscription.service';
import {Subject} from 'rxjs';
import {SocketRequest} from '../../../Request';
import {URLGame} from '../../model/url';
import {map} from 'rxjs/operators';

@Injectable()
export class GameOnService {

  public messages: Subject<SocketRequest>;

  constructor(private wsService: WebsocketService,
              private subscription: SubscriptionService) {
    this.messages = wsService
      .connect(URLGame)
      .pipe(
        map((response: MessageEvent): SocketRequest => {
          const data = JSON.parse(response.data);
          console.log(data);
          let playerList = [];
          if (JSON.stringify(data).includes('players')) {
            playerList = JSON.parse(data.players);
            console.log(playerList);
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
            console.log(player);
            return player;
          });
          console.log(data);
          return data;
        })) as Subject<SocketRequest>;
  }
}
