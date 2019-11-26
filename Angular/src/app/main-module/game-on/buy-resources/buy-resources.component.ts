import {Component, OnInit, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-buy-resources',
  templateUrl: './buy-resources.component.html',
  styleUrls: ['./buy-resources.component.css']
})
export class BuyResourcesComponent implements OnInit {
  @Output() valueChange = new EventEmitter();
  ressourceNb = 0;

  constructor() {
  }

  buyResource(): void {
    this.valueChange.emit(this.ressourceNb);
  }

  ngOnInit() {
  }

}
