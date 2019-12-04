import {WebsocketService} from './service/webSocketService/websocket.service';
import {LobbyService} from './service/lobbyService/lobby.service';

import {Component} from '@angular/core';
import {SocketRequest} from '../Request';

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
    const i = {
      negociationID: '179f781a-1be1-40df-882d-e7dd74bacb48',
      response: 'START_NEGOTIATE'
    };
    const k = {
      negociationID: '1be1-40df-882d-e7dd74bacb48',
      response: 'START_NEGOTIATE'
    };
    const j = {
      negociationID: '179f781a-1be1-40df-882dacb48',
      response: 'START_NEGOTIATE'
    };
    this.test1.push(i);
    this.test1.push(k);
    this.test1.push(j);
  }


}
