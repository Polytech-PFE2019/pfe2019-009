import {Component, OnInit, EventEmitter, Output, Input, OnDestroy} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {BuyResourceService} from '../../../service/resources/buy-resource.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-buy-resources',
  templateUrl: './buy-resources.component.html',
  styleUrls: ['./buy-resources.component.css']
})
export class BuyResourcesComponent implements OnInit, OnDestroy {
  resourceNb = 0;
  isVisible = false;
  multiple = 1;
  price = 0;
  subCurrentMonney: Subscription;
  currentMonney = 30;

  constructor(private nzMessageService: NzMessageService,
              private resourceService: BuyResourceService) {
  }

  ngOnInit() {
    this.subCurrentMonney = this.resourceService.currentMonney$.subscribe(data => {
      this.currentMonney = data;
    });
  }

  popConfirm(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.price = this.resourceNb * this.multiple;
    console.log('price is ' + this.price);
    this.resourceService.sendResourcesBuying(this.resourceNb);
    this.resourceService.sendPayment(this.price);
    this.multiple = 2 * this.multiple;
    console.log('multiple' + this.multiple);
    this.isVisible = false;
    this.nzMessageService.info('Achat réussi');
    this.resourceNb = 0;
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Achat annulé');
  }

  ngOnDestroy(): void {
    this.subCurrentMonney.unsubscribe();
  }

}
