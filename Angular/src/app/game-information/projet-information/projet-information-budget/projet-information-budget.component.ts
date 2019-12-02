import {Component, OnInit} from '@angular/core';
import {GameOnService} from "../../../service/gameOnService/game-on.service";

@Component({
  selector: 'app-projet-information-budget',
  templateUrl: './projet-information-budget.component.html',
  styleUrls: ['./projet-information-budget.component.css']
})
export class ProjetInformationBudgetComponent implements OnInit {

  list: any = [];
  money = 0;

  constructor(private gameService: GameOnService) {
  }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }

    this.money = this.gameService.costProject;
    console.log(this.money);
  }

}
