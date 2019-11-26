import {Component,Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-person-information',
  templateUrl: './person-information.component.html',
  styleUrls: ['./person-information.component.css']
})
export class PersonInformationComponent implements OnInit {
  baseRes = 18;
  addRes = 0;
  pay = 0;
  baseMoney = 30;
  @Input() set getDataFromParent( getDataFromParent: number) {
    this.addRes = getDataFromParent;
  }
  @Input() set getDataFromActivity(getDataFromActivity:number) {
    this.pay = getDataFromActivity;
  }

  constructor() {
  }

  ngOnInit() {
  }

}
