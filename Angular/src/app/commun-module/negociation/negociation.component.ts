import {Component, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-negociation',
  templateUrl: './negociation.component.html',
  styleUrls: ['./negociation.component.css']
})
export class NegociationComponent implements OnInit {
  @Input() fromUser: number;
  @Input() toUser: number;


  constructor() { }

  ngOnInit() {
  }

}
