import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {map} from 'rxjs/operators';
import {WebsocketService} from './websocket.service';
import {SocketRequest} from 'src/Request';
import {URL} from "../model/url";
import {Globals} from "../globals";


@Injectable()
export class LobbyService {
  roomId: number;
  userName: string;

  public messages: Subject<SocketRequest>;
  globals: Globals;
  userID: string;

  constructor(private wsService: WebsocketService, globals: Globals) {
    this.globals = globals;
    this.messages = wsService
      .connect(URL)
      .pipe(
        map((response: MessageEvent): SocketRequest => {
          const data = JSON.parse(response.data);
          console.log(data);
          if (JSON.stringify(data).includes('userID')) {
            this.globals.userID = data.userID;
            this.userID = this.globals.userID;
          }
          this.roomId = data.roomID;
          return data as SocketRequest;

        })) as Subject<SocketRequest>;
  }
}
