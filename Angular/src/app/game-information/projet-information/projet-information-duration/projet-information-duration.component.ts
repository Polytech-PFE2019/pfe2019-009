import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-projet-information-duration',
  templateUrl: './projet-information-duration.component.html',
  styleUrls: ['./projet-information-duration.component.css']
})
export class ProjetInformationDurationComponent implements OnInit {

  list: any = [];

  constructor() {
  }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }
  }

}
