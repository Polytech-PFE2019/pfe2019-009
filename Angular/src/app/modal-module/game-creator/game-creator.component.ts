import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-game-creator',
  templateUrl: './game-creator.component.html',
  styleUrls: ['./game-creator.component.css']
})
export class GameCreatorComponent implements OnInit {
  isCreatingSalon: any;

  constructor(private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
  }

  handleCancel() {
    this.isCreatingSalon = false;
  }

  handleOk() {
    this.router.navigate(['gameroom']);
    this.isCreatingSalon = false;
  }
}
