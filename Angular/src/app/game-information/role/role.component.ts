import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ConfirmRoleComponent} from '../../modal-module/role-confirm/confirm-role/confirm-role.component';
import {Router} from '@angular/router';
import {LobbyService} from "../../service/lobby.service";
import {Globals} from "../../globals";
import {SocketRequest} from "../../../Request";
import {ObserveOnSubscriber} from "rxjs/internal/operators/observeOn";
import {Observable} from "rxjs";

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  @Input() role: any = null;
  @Input() roomID: string;
  @Output() checkNum = new EventEmitter();
  @Input() testChecked = false;
  @ViewChild(ConfirmRoleComponent, {static: true})
  confirmRole;
  index1 = 0;
  checked = false;
  numOfPlayers = 0;
  globals: Globals;
  userID: string;
  testId: string;
  ID: string;

  constructor(private router: Router,
              globals: Globals,
              private lobbyService: LobbyService) {
    lobbyService.messages.subscribe(data => {
      console.log(data);
      if (data.roomID === null) {
        this.ID = data.roomID;
      }
    });
    this.globals = globals;
    this.testId = globals.userID;
  }

  ngOnInit() {
  }

  confirmRoles() {
    this.confirmRole.isVisible = true;
  }

  getRole($event: MouseEvent) {
  }

// {    “REQUEST” : ‘CHOOSE_ROLE’,
// “roomID” : 1,   “userID” : 2,
// “roleID” : 2}

  getChange() {
    console.log(this.globals.userID);
    const req = {
      request: 'CHOOSE_ROLE',
      roomID: this.globals.roomID.toString(),
      userID: this.globals.userID,
      roleID: this.role.id.toString()
    };
    console.log(req);
    this.lobbyService.messages.next(req as SocketRequest);
    const data = {
      role: this.role.id,
      checked: this.testChecked
    };
    this.checkNum.emit(data);
  }
}
