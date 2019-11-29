import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-confirm-role',
  templateUrl: './confirm-role.component.html',
  styleUrls: ['./confirm-role.component.css']
})
export class ConfirmRoleComponent implements OnInit {
  isVisible = false;
  @Input() role = '';
  constructor(private router: Router) { }

  ngOnInit() {
  }

  handleCancel() {
    this.isVisible = false;
  }

  handleOk() {
    this.isVisible = false;
    this.router.navigate(['gameon']);
  }
}
