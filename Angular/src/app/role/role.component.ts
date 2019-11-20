import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  @Input() role: any = null;
  index1 = 0;
  src = '../../assets/Architecte.png';

  constructor() {
  }

  ngOnInit() {
  }

}
