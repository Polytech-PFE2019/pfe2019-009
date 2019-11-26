import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-buy-resources',
  templateUrl: './buy-resources.component.html',
  styleUrls: ['./buy-resources.component.css']
})
export class BuyResourcesComponent implements OnInit {
  @Output() resourceChange = new EventEmitter();
  @Output() moneyChange = new EventEmitter();
  @Input() moneyRemain:number;
  @Output() valueChange = new EventEmitter();
  resourceNb = 0;
  isVisible = false;
  multiple = 1;
  price = 0;

  constructor(private nzMessageService: NzMessageService) {}

  popConfirm(): void {
    this.isVisible = true;
  }

  buyResource(): void {
    this.price = this.resourceNb * this.multiple;
    this.resourceChange.emit(this.resourceNb);
    this.moneyChange.emit(this.price);
    this.nzMessageService.info('Achat réussi');
  }

  handleOk(): void {
    this.buyResource();
    this.moneyRemain -= this.resourceNb * this.multiple;
    this.multiple =2;
    this.moneyRemain = this.moneyRemain/this.multiple
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Achat annulé');
  }

  ngOnInit() {
  }

}
