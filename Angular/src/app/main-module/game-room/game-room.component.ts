import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Roles} from '../../model/roles';
import {RoleComponent} from '../../game-information/role/role.component';
import {ActivatedRoute, Router} from '@angular/router';
import {SocketRequest} from '../../../Request';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {Subscription} from 'rxjs';
import {HttpParams} from '@angular/common/http';
import {Role} from '../../model/role';
import {Globals} from '../../globals';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {Player} from 'src/app/Player';
import {PlayerdataService} from 'src/app/playerdata.service';
import {THIS_EXPR} from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css']
})
export class GameRoomComponent implements OnInit, OnDestroy {

  @ViewChild(RoleComponent, {static: true})
  Role;
  roles: any[] = [];
  checkedID = null;
  userName = '9999';
  users = [];
  roomID: string;
  userID: string;
  subUserId: Subscription;
  subRoomId: Subscription;
  roleID: any;
  userReady = 0;
  hostID: string;
  isLoding = false;

  constructor(private router: Router,
              private globals: Globals,
              private gameService: GameOnService,
              private activatedRoute: ActivatedRoute,
              private subscriptionService: SubscriptionService,
              private lobbyService: LobbyService, private playerDataService: PlayerdataService) {
    for (let r of Roles) {
      r = new Role(r);
      this.roles = this.roles.concat(r);
    }
  }

  ngOnInit() {
    this.lobbyService.messages.subscribe(data => {
      console.log(data);
      this.userReady = 0;
      console.log(data.gameID);
      if (data.gameID !== undefined) {
        const params = {
          params: new HttpParams()
            .set('roleID', this.roleID)
            .set('userName', this.userName)
            .set('gameID', data.gameID)
        };
        const req = {
          request: 'JOIN_GAME',
          gameID: data.gameID.toString(),
          userID: this.userID
        };
        this.subscriptionService.sendGameId(data.gameID);
        this.gameService.messages.next(req as SocketRequest);
        // this.isLoding = true;
        // setTimeout(() => {
        this.router.navigate(['gameon'], {queryParams: params});
        // }, 5000);
      } else {
        switch (data.response) {
          case 'UPDATE':
            this.users = data.players;
            this.hostID = data.hostID;

            for (const r of this.roles) {
              let taken = false;
              if (r.ready) {
                this.userReady++;
              }
              for (const player of this.users) {
                if (r.id === player.roleID) {
                  r.addThisAttributes(player);
                  taken = true;
                }
              }
              if (!taken) {
                r.removeAttribute();
              }
            }
            console.log(this.roles);
            break;
          case 'START_GAME':
            this.goToLoadingPage(data.gameID);
            break;
        }
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
      if (this.checkedID === null) {
        this.checkedID = $event.role;
        for (const r of this.roles) {
          if (r.id === $event.role) {
            r.choosed = true;
            r.ready = false;
          }
        }
      } else {
        for (const r of this.roles) {
          if ($event.role === r.id) {
            if (r.ready) {
              this.ready();
            }
            r.choosed = true;
            r.ready = false;
          }
          if (this.checkedID === r.id) {
            r.username = '';
            if (r.ready) {
              this.ready();
            }
            r.choosed = false;
            r.ready = false;
          }
        }
        this.checkedID = $event.role;
      }
    } else {
      for (const r of this.roles) {
        if (r.id === $event.role) {
          r.choosed = false;
          r.ready = false;
          r.username = '';
          this.checkedID = null;
        }
      }
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


    this.lobbyService.messages.next(message as SocketRequest);
  }

  goToLoadingPage(gameID) {
    const players = {
      playerID: this.userID.toString(),
      gameID: gameID.toString()
    };

    this.playerDataService.player = players as Player;
    this.router.navigate(['loading']);

  }

  ready() {
    console.log(event);
    const req = {
      request: 'CHANGE_STATUS',
      roomID: this.roomID.toString(),
      userID: this.userID.toString()
    };
    console.log(req);
    this.lobbyService.messages.next(req as SocketRequest);
  }

}