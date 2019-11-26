import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-buy-resources',
  templateUrl: './buy-resources.component.html',
  styleUrls: ['./buy-resources.component.css']
})
export class BuyResourcesComponent implements OnInit {
  @Output() valueChange = new EventEmitter();
  @Input() moneyRemain: number;
  ressourceNb = 0;
  isVisible = false;

  constructor(private nzMessageService: NzMessageService) {
  }

  popConfirm(): void {
    this.isVisible = true;

  }

  buyResource(): void {
    this.valueChange.emit(this.ressourceNb);
    this.nzMessageService.info('Achat réussi');
  }

  handleOk(): void {
    this.buyResource();
    this.moneyRemain -= this.ressourceNb;
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Achat annulé');
  }

  ngOnInit() {
  }

}
