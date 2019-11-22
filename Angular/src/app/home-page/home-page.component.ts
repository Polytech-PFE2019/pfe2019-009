import {Component, OnInit, ViewChild} from '@angular/core';
import {GameCreatorComponent} from '../modal-module/game-creator/game-creator.component';
import {RoomService} from '../service/room.service';
import {SocketRequest} from '../../Request';
import {LobbyService} from '../service/lobby.service';
import {RoomsService} from '../rooms.service';
import {Rooms} from '../rooms';
import {Router} from "@angular/router";
import {Globals} from "../globals";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  @ViewChild(GameCreatorComponent, {static: true})
  gameCreator;
  value: any;
  username: string;
  showJoinSalon = false;
  listOfDisplayData: Rooms[] = [];

  constructor(private roomsService: RoomsService,
              private globals: Globals,
              private router: Router,
              private lobbyService: LobbyService) {
  }

  ngOnInit() {
    this.roomsService.getRoomsInfos().subscribe(data => {
      console.log(data);
      this.listOfDisplayData = data;
    });
  }

  createASalon() {
    this.gameCreator.isCreatingSalon = true;
  }

  testdd($event: any) {
    console.log($event);
  }


  enterUsername() {
    this.username = this.value;
    this.globals.username = this.value;
  }

  joinASalon() {
    this.showJoinSalon = true;
  }

  handleOk() {
    this.showJoinSalon = false;
  }

  cancel() {
    this.showJoinSalon = false;
  }

  joinSalon(data) {
    console.log('Join game');
    const req = {
      request: 'JOIN_GAME',
      roomID: data.roomID,
      username: this.username
    };

    this.lobbyService.messages.next(req as SocketRequest);
    this.router.navigate(['gameroom']);
  }
}
