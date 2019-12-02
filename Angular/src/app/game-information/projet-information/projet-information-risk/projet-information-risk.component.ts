import {Component, OnInit} from '@angular/core';
import {GameOnService} from "../../../service/gameOnService/game-on.service";

@Component({
  selector: 'app-projet-information-risk',
  templateUrl: './projet-information-risk.component.html',
  styleUrls: ['./projet-information-risk.component.css']
})
export class ProjetInformationRiskComponent implements OnInit {
  list: any = [];
  risks = 0;

  constructor(private gameService: GameOnService) {
  }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }

    this.risks = this.gameService.failureProject;
    console.log(this.risks);
  }

}
