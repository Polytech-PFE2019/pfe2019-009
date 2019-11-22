import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { map } from 'rxjs/operators';
import { WebsocketService } from "./websocket.service";
import { SocketRequest } from 'src/Request';

const URL = "ws://localhost:8080/name";



@Injectable()
export class LobbyService {
  public messages: Subject<SocketRequest>;

  constructor(wsService: WebsocketService) {
    this.messages = <Subject<SocketRequest>>wsService
      .connect(URL)
      .pipe(

        map((response: MessageEvent): SocketRequest => {
          let data = JSON.parse(response.data);
          console.log(data)
          return data as SocketRequest

        }));
  }
}