import {Component, OnInit, EventEmitter, Output, Input, OnDestroy} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {BuyResourceService} from '../../../service/resources/buy-resource.service';
import {Subscription} from 'rxjs';
import {GameOnService} from '../../../service/gameOnService/game-on.service';
import {SubscriptionService} from '../../../service/subscriptionSerivce/subscription.service';
import {SocketRequest} from '../../../../Request';
import {NzNotificationService} from 'ng-zorro-antd';

@Component({
  selector: 'app-buy-resources',
  templateUrl: './buy-resources.component.html',
  styleUrls: ['./buy-resources.component.css']
})
export class BuyResourcesComponent implements OnInit, OnDestroy {
  @Output() sendBuy = new EventEmitter();
  resourceNb = 0;
  isVisible = false;
  multiple = 2;
  price = 0;
  subCurrentMonney: Subscription;
  currentMonney = 30;
  gameID: string;
  userID: string;
  subCost: Subscription;
  currentActivity: any;
  roleID : any;
  cost: any = null;
  roles: any[] = [];

  constructor(private nzMessageService: NzMessageService,
              private gameService: GameOnService,
              private notification: NzNotificationService,
              private subscription: SubscriptionService,
              private resourceService: BuyResourceService) {
  }

  ngOnInit() {
    this.currentMonney = this.subscription.myRole.money;

    this.roleID = this.subscription.myRole.id;

    this.currentActivity = this.subscription.current;
    this.subscription.currentActivity$.subscribe(data => {
      this.currentActivity = data;
      this.calculateMultiple()
    });
    this.calculateMultiple();

    this.subCurrentMonney = this.resourceService.currentMonney$.subscribe(data => {
      this.currentMonney = data;
    });


    this.subCost = this.subscription.costs$.subscribe(data => {
      console.log(data);
      this.cost = data;
    });

    this.cost = this.subscription.costInital;




    // this.gameService.messages.subscribe(data => {
    //   console.log(data);
    // });

    // this.subGameId = this.subscription.gameID$.subscribe(id => {
    //   this.gameID = id;
    // });


    this.gameID = this.subscription.gameID;
    this.userID = this.subscription.userId;

    // this.subUserId = this.subscription.userID$.subscribe(data => {
    //   this.userID = data;
    // });
  }

  popConfirm(): void {
    this.price = this.resourceNb * this.multiple;
    if (this.currentMonney >= this.price) {
      this.isVisible = true;
    } else {
      this.nzMessageService.info('Votre agent ne suffit pas');
    }
  }

  handleOk(): void {
    this.sendBuy.emit(true);
    this.price = this.resourceNb * this.multiple;
    // this.notification.blank('Activité effectuée',
    //   'Vous avez acheté ' + this.resourceNb + ' resources',
    //   {nzDuration: 35});
    console.log('price is ' + this.price);

    this.multiple = 2;
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
    this.resourceNb = 0;
    this.gameService.messages.next(req as SocketRequest);
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Achat annulé');
  }

  ngOnDestroy(): void {
    this.subCurrentMonney.unsubscribe();
    this.subCost.unsubscribe();
  }

  getRoleById(id) {
    return this.roles.filter(next => next.id === id)[0];
  }

  private calculateMultiple() {
    for (let buyingAction of this.currentActivity.buyingActions){
      if(buyingAction.roleID === this.roleID){
        this.multiple = 1;
      }
    }
  }
}
