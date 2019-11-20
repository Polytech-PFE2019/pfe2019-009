import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ConfirmRoleComponent} from '../modal-module/role-confirm/confirm-role/confirm-role.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  @Input() role: any = null;
  @Output() checkNum = new EventEmitter();
  @ViewChild(ConfirmRoleComponent, {static: true})
  confirmRole;
  index1 = 0;
  checked = false;
  numOfPlayers = 0;

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  confirmRoles() {
    this.confirmRole.isVisible = true;
  }

  getRole($event: MouseEvent) {
  }

  getChange() {
    this.checkNum.emit(this.checked);
  }
}
