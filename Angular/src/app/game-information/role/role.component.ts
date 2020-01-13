import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {ConfirmRoleComponent} from '../../modal-module/role-confirm/confirm-role/confirm-role.component';
import {ActivatedRoute, Router} from '@angular/router';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {SocketRequest} from '../../../Request';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit, OnDestroy {
  @Input() role: any = null;
  @Output() checkNum = new EventEmitter();
  @Input() readyed = false;
  @Input() roomID: string;
  @Input() choosed = false;
  @Input() disableChoose = false;
  @ViewChild(ConfirmRoleComponent, {static: true})
  confirmRole;
  index1 = 0;
  numOfPlayers = 0;
  userID: string;
  subUserId: Subscription;

  roleChoosed: any[] = [];
  ifReady = false;
  users: any[] = [];

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private subscription: SubscriptionService,
              private lobbyService: LobbyService) {
  }

  ngOnInit() {
    this.subUserId = this.subscription.userID$.subscribe(data => {
      console.log(data);
      this.userID = data;
    });
  }

  getChange() {
    if (this.role.choosed) {
      console.log(this.userID);
      const req = {
        request: 'CHOOSE_ROLE',
        roomID: this.roomID.toString(),
        userID: this.userID,
        roleID: this.role.id.toString()
      };
      console.log(req);
      this.lobbyService.messages.next(req as SocketRequest);
    } else {
      const req = {
        request: 'CHOOSE_ROLE',
        roomID: this.roomID,
        userID: this.userID,
        roleID: '0'
      };
      console.log(req);
      this.lobbyService.messages.next(req as SocketRequest);
    }
    const data = {
      role: this.role.id,
      checked: this.role.choosed
    };
    this.checkNum.emit(data);
  }

  ngOnDestroy(): void {
    this.subUserId.unsubscribe();
  }

  ready() {
    const req = {
      request: 'CHANGE_STATUS',
      roomID: this.roomID,
      userID: this.userID
    };
    console.log(req);
    this.lobbyService.messages.next(req as SocketRequest);
  }

}
