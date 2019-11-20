import {Component, OnInit, ViewChild} from '@angular/core';
import {Roles} from '../model/roles';
import {RoleComponent} from '../role/role.component';
import {Router} from '@angular/router';

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

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  getCheckedNum($event: any) {
    if ($event) {
      this.numOfPlayers++;
    } else {
      this.numOfPlayers--;
    }
    console.log(this.numOfPlayers);
  }

  gameStart() {
    console.log('Game start');
    this.router.navigate(['gameon']);
  }
}
