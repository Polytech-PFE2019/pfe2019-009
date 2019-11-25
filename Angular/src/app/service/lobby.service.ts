import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {map} from 'rxjs/operators';
import {WebsocketService} from './websocket.service';
import {SocketRequest} from 'src/Request';
import {URL} from '../model/url';
import {Globals} from '../globals';


@Injectable()
export class LobbyService {
  roomId: number;
  userName: string;

  public messages: Subject<SocketRequest>;
  globals: Globals;
  userID: string;

  constructor(private wsService: WebsocketService, globals: Globals) {
    this.globals = globals;
    this.messages = <Subject<SocketRequest>> wsService
      .connect(URL)
      .pipe(
        map((response: MessageEvent): SocketRequest => {
          const data = JSON.parse(response.data);
          let playerList = [];
          if (JSON.stringify(data).includes('players')) {
            playerList = JSON.parse(data.players);
          }
          if (JSON.stringify(data).includes('userID')) {
            this.globals.userID = data.userID;
            this.userID = this.globals.userID;
          }
          if (JSON.stringify(data).includes('roomID')) {
            this.globals.roomID = data.roomID;
          }
          data.players = playerList.map(player => {
            return JSON.parse(player);
          });
          console.log(data);
          return data;
        }));
  }
}
