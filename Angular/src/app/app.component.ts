import {WebsocketService} from './service/websocket.service';
import {LobbyService} from './service/lobby.service';

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
