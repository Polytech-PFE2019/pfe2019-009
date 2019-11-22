import {Component, OnInit, ViewChild} from '@angular/core';
import {Roles} from '../model/roles';
import {RoleComponent} from '../game-information/role/role.component';
import {Router} from '@angular/router';
import {SocketRequest} from '../../Request';
import {LobbyService} from '../service/lobby.service';
import {Player} from '../Player';
import {Globals} from "../globals";

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css']
})
export class GameRoomComponent implements OnInit {

  @ViewChild(RoleComponent, {static: true})
  Role;
  roles: any = Roles;
  numOfPlayers = 0;
  chekedItem = [];
  checkedID: number;
  userName: string;
  globals: Globals;
  users = [];

  constructor(private router: Router,
              private global: Globals,
              private lobbyService: LobbyService) {
    this.globals = global;
  }

  ngOnInit() {
    this.userName = this.globals.username;
  }

  getCheckedNum($event: any) {
    // if ($event) {
    //   this.numOfPlayers++;
    // } else {
    //   this.numOfPlayers--;
    // }
    // console.log(this.numOfPlayers);
    if ($event.checked) {
      this.checkedID = $event.role;
      this.chekedItem.push($event);
      console.log($event);
    }
  }

  gameStart() {
    console.log('Game start');
    const message = {
      request: 'START_GAME',
      roomID: '0',
      userID: '2'
    };
    this.lobbyService.messages.next(message as SocketRequest);
    this.router.navigate(['gameon']);
  }
}
