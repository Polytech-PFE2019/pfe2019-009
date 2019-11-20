import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-game-creator',
  templateUrl: './game-creator.component.html',
  styleUrls: ['./game-creator.component.css']
})
export class GameCreatorComponent implements OnInit {
  isCreatingSalon: any;
  @Output() test = new EventEmitter();

  constructor(private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
  }

  handleCancel() {
    this.isCreatingSalon = false;
  }

  handleOk() {
    this.test.emit(1);
    this.router.navigate(['gameroom']);
    this.isCreatingSalon = false;
  }
}
