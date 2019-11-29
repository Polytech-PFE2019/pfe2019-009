import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Steps} from '../../../model/step';
import {Risks} from '../../../model/risks';

@Component({
  selector: 'app-risk-container',
  templateUrl: './risk-container.component.html',
  styleUrls: ['./risk-container.component.css']
})
export class RiskContainerComponent implements OnInit {
  @Input() risk: any;

  constructor() { }
  ngOnInit() {
    console.log(this.risk);
  }

}
