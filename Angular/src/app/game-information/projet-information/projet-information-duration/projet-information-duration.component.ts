import {Component, OnInit} from '@angular/core';
import {GameOnService} from "../../../service/gameOnService/game-on.service";

@Component({
  selector: 'app-projet-information-duration',
  templateUrl: './projet-information-duration.component.html',
  styleUrls: ['./projet-information-duration.component.css']
})
export class ProjetInformationDurationComponent implements OnInit {

  list: any = [];
  days = 0;

  constructor(private gameService: GameOnService) {
  }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }

    this.days = this.gameService.delayProject;
    console.log(this.days);
  }

}
