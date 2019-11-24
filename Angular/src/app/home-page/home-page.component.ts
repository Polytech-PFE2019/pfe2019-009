import {Component, OnInit, ViewChild} from '@angular/core';
import {GameCreatorComponent} from '../modal-module/game-creator/game-creator.component';
import {RoomService} from '../service/room.service';
import {SocketRequest} from '../../Request';
import {LobbyService} from '../service/lobby.service';
import {RoomsService} from '../rooms.service';
import {Rooms} from '../rooms';
import {Router} from '@angular/router';
import {Globals} from '../globals';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  @ViewChild(GameCreatorComponent, {static: true})
  gameCreator;
  value = '';
  username = '';
  showJoinSalon = false;
  listOfDisplayData: Rooms[] = [];

  constructor(private roomsService: RoomsService,
              private globals: Globals,
              private router: Router,
              private message: NzMessageService,
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

  joinSalon(data) {
    if (this.value !== '') {
      console.log('Join game');
      const req = {
        request: 'JOIN_GAME',
        roomID: data.roomID,
        username: this.value
      };
      console.log(req);
      this.lobbyService.messages.next(req as SocketRequest);
      this.router.navigate(['gameroom']);
    } else {
      this.message.warning('Entréez votre nom!');
    }
  }

  getUsername() {
    this.globals.username = this.value;
  }
}
