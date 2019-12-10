import {WebsocketService} from './service/webSocketService/websocket.service';
import {LobbyService} from './service/lobbyService/lobby.service';

import {Component} from '@angular/core';
import {SocketRequest} from '../Request';
import {transition, trigger, useAnimation} from '@angular/animations';
import {bounce, flipOutX} from 'ng-animate';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [WebsocketService, LobbyService],
})
export class AppComponent {

  test: SocketRequest = new SocketRequest();
  test1 = [];
  flipOutX: any;
  isVanished = false;
  isTest = false;
  data: any[] = [];
  content = '11111';

  ngOnInit(){

  }
  constructor() {
    this.data = [
      {
        src: '',
        activity: "jour",
        money: 3,
        result: -2,
      }
    ];
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

    const jj = {
      negociationID: '179f781a-1be1-40df-882dacb48',
      response: 'START_NEGOTIATE'
    };

    const jjj = {
      negociationID: '179f781a-1be1-40df-882dacb48',
      response: 'START_NEGOTIATE'
    };
    this.test1.push(i);
    this.test1.push(k);
    this.test1.push(j);
    this.test1.push(jj);
    this.test1.push(jjj);
  }

  testFlip() {
    if (this.flipOutX === null) {
      this.flipOutX = true;
    } else {
      this.flipOutX = !this.flipOutX;
    }
  }

  open() {
    this.isTest = true;
  }

  close() {
    this.isTest = false;
  }
}
