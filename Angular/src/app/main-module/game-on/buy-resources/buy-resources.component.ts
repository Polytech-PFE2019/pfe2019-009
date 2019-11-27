import {Component, OnInit, EventEmitter, Output, Input, OnDestroy} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {BuyResourceService} from '../../../service/resources/buy-resource.service';
import {Subscription} from 'rxjs';
import {GameOnService} from '../../../service/gameOnService/game-on.service';
import {SubscriptionService} from '../../../service/subscriptionSerivce/subscription.service';
import {SocketRequest} from '../../../../Request';

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
  gameID: string;
  subGameId: Subscription;
  userID: string;
  subUserId: Subscription;

  constructor(private nzMessageService: NzMessageService,
              private gameService: GameOnService,
              private subscription: SubscriptionService,
              private resourceService: BuyResourceService) {
  }

  ngOnInit() {
    this.subCurrentMonney = this.resourceService.currentMonney$.subscribe(data => {
      this.currentMonney = data;
    });

    this.gameService.messages.subscribe(data => {
      console.log(data);
    });

    this.subGameId = this.subscription.gameID$.subscribe(id => {
      this.gameID = id;
    });

    this.subUserId = this.subscription.userID$.subscribe(data => {
      this.userID = data;
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
    const req = {
      request: 'BUY_RESOURCES',
      gameID: this.gameID.toString(),
      userID: this.userID,
      amount: this.resourceNb.toString()
    };
    console.log(req);
    this.gameService.messages.next(req as SocketRequest);
    this.resourceNb = 0;
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Achat annulé');
  }

  ngOnDestroy(): void {
    this.subCurrentMonney.unsubscribe();
    this.subUserId.unsubscribe();
    this.subGameId.unsubscribe();
  }

}
