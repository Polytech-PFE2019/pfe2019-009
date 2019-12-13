import {WebsocketService} from './service/webSocketService/websocket.service';
import {LobbyService} from './service/lobbyService/lobby.service';

import {Component, OnInit} from '@angular/core';
import {SocketRequest} from '../Request';
import {transition, trigger, useAnimation} from '@angular/animations';
import {bounce, flipOutX} from 'ng-animate';
import {Roles} from "./model/roles";

interface ItemData {
  href: string;
  title: string;
  avatar: string;
  description: string;
  content: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [WebsocketService, LobbyService],
})
export class AppComponent implements OnInit {

  test: SocketRequest = new SocketRequest();
  test1 = [];
  flipOutX: any;
  isVanished = false;
  isTest = false;
  data: any[] = [];
  roles = Roles;
  count = 6;

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

  ngOnInit(): void {
    this.loadData(1);
  }

  loadData(pi: number): void {
    this.data = new Array(5).fill({}).map((_, index) => {
      return {
        href: 'http://ant.design',
        title: `ant design part ${index} (page: ${pi})`,
        avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
        description: 'Ant Design, a design language for background applications, is refined by Ant UED Team.',
        content:
          'We supply a series of design principles, practical patterns and high quality design resources ' +
          '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.'
      };
    });
  }

  clearBadge() {
    this.count = 0;
  }
}
