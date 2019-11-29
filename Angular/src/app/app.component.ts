import {WebsocketService} from './service/webSocketService/websocket.service';
import {LobbyService} from './service/lobbyService/lobby.service';

import {Component} from '@angular/core';
import {SocketRequest} from "../Request";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [WebsocketService, LobbyService]
})
export class AppComponent {

  test: SocketRequest = new SocketRequest();
  test1 = [];

  constructor() {
    if (this.test.gameID === undefined) {
      console.log('asdfkjalsjdflas');
    } else {
      console.log('1230');
    }

    this.test1.push('sdskfhakshdfk');
    console.log(this.test1);
  }

}
