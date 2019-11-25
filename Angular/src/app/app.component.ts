import {WebsocketService} from './service/webSocketService/websocket.service';
import {LobbyService} from './service/lobbyService/lobby.service';

import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [WebsocketService, LobbyService]
})
export class AppComponent {


  constructor() {
  }

}
