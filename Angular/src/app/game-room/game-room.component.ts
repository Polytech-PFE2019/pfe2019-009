import {Component, OnInit} from '@angular/core';
import {Roles} from '../model/roles';

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css']
})
export class GameRoomComponent implements OnInit {

  roles: any = Roles;

  constructor() {
  }

  ngOnInit() {
  }

}
