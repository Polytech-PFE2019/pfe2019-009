import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Roles} from '../model/roles';
import {RoleComponent} from '../game-information/role/role.component';
import {ActivatedRoute, Router} from '@angular/router';
import {SocketRequest} from '../../Request';
import {LobbyService} from '../service/lobbyService/lobby.service';
import {SubscriptionService} from '../service/subscriptionSerivce/subscription.service';
import {Subscription} from 'rxjs';
import {HttpParams} from '@angular/common/http';
import {Role} from '../model/role';
import {Globals} from "../globals";

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css']
})
export class GameRoomComponent implements OnInit, OnDestroy {

  @ViewChild(RoleComponent, {static: true})
  Role;
  roles: any[] = [];
  chekedItem = [];
  checkedID: number;
  userName = '9999';
  users = [];
  roomID: string;
  userID: string;
  subUserId: Subscription;
  subRoomId: Subscription;
  roleID: any;
  roleChoosed: any[] = [];
  userReady = 0;
  hostID: string;

  constructor(private router: Router,
              private globals: Globals,
              private activatedRoute: ActivatedRoute,
              private subscriptionService: SubscriptionService,
              private lobbyService: LobbyService) {
    for (let r of Roles) {
      r = new Role(r);
      this.roles = this.roles.concat(r);
    }
  }

  ngOnInit() {
    this.lobbyService.messages.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'UPDATE':
          this.users = data.players;
          this.hostID = data.hostID;
          for (const r of this.roles) {
            for (const player of this.users) {
              this.roleChoosed = this.roleChoosed.concat(player.roleID);
              if (player.ready) {
                this.userReady++;
              }
              if (r.id === player.roleID) {
                r.addAttribute(player);
              }
            }
          }
          console.log(this.roles);
          console.log(this.roleChoosed);
          break;
      }
    });

    this.subUserId = this.subscriptionService.userID$.subscribe(data => {
      console.log(data);
      this.userID = data;
    });

    this.subRoomId = this.subscriptionService.roomID$.subscribe(roomId => {
      console.log(roomId);
      this.roomID = roomId;
    });

    this.userName = this.lobbyService.username;
  }

  ngOnDestroy() {
    this.subUserId.unsubscribe();
    this.subRoomId.unsubscribe();
  }

  getCheckedNum($event: any) {
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
      roomID: this.roomID.toString(),
      userID: this.userID.toString()
    };
    console.log(message);
    const params = {
      params: new HttpParams()
        .set('roleID', this.roleID)
        .set('userName', this.userName)
    };
    this.lobbyService.messages.next(message as SocketRequest);
    this.router.navigate(['gameon'], {queryParams: params});
  }

}
