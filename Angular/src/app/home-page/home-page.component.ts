import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { GameCreatorComponent } from '../modal-module/game-creator/game-creator.component';
import { RoomService } from '../service/roomService/room.service';
import { SocketRequest } from '../../Request';
import { LobbyService } from '../service/lobbyService/lobby.service';
import { RoomsService } from '../rooms.service';
import { Rooms } from '../rooms';
import { Router } from '@angular/router';
import { Globals } from '../globals';
import { NzMessageService } from 'ng-zorro-antd';
import { SubscriptionService } from '../service/subscriptionSerivce/subscription.service';
import { Subscription } from 'rxjs';
import { HttpParams } from "@angular/common/http";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit, OnDestroy {

  @ViewChild(GameCreatorComponent, { static: true })
  gameCreator;
  value = '';
  username = '';
  listOfDisplayData: Rooms[] = [];
  roomID: any;
  userName: string;
  lobbyIntervalID;

  constructor(private roomsService: RoomsService,
    private globals: Globals,
    private router: Router,
    private subscriptionService: SubscriptionService,
    private message: NzMessageService,
    private lobbyService: LobbyService) {
  }

  ngOnInit() {
    this.getAllLobby();

    this.lobbyIntervalID = setInterval(() => {
      this.getAllLobby();
    }, 5000
    )

    this.lobbyService.messages.subscribe(data => {
      console.log(data);
      if (JSON.stringify(data).includes('userID')) {
        console.log(data.userID);
      }
    });


  }

  getAllLobby() {
    this.roomsService.getRoomsInfos().subscribe(data => {
      console.log(data);
      this.listOfDisplayData = data;
    });
  }

  createASalon() {
    if (this.value !== '') {
      console.log('send userName');
      this.subscriptionService.sendUserName(this.userName);
      this.gameCreator.isCreatingSalon = true;
    } else {
      this.message.warning('Entréez votre nom!');
    }
  }

  testdd($event: any) {
    console.log($event);
  }

  joinSalon(data) {
    if (this.value !== '') {
      this.subscriptionService.sendUserName(this.value);
      console.log('Join game');
      const req = {
        request: 'JOIN_GAME',
        roomID: data.roomID,
        username: this.value
      };
      console.log(req);
      this.router.navigate(['gameroom']);
      this.lobbyService.messages.next(req as SocketRequest);
    } else {
      this.message.warning('Entréez votre nom!');
    }
  }

  getUsername() {
    this.userName = this.value;
  }

  getDisabled() {
    if (this.value === '') {
      this.message.warning('Entréez votre nom!');
      return false;
    } else {
      return true;
    }
  }

  ngOnDestroy() {
    clearInterval(this.lobbyIntervalID);
  }
}
