import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {Subscription} from 'rxjs';
import {ActionSet} from '../../model/action';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {NzMessageService} from 'ng-zorro-antd';
import {BuyResourceService} from '../../service/resources/buy-resource.service';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.css']
})
export class StepComponent implements OnInit, OnDestroy {
  @Input() step: any = null;
  @Output() currentStep = new EventEmitter();
  @Output() sendTestClick = new EventEmitter();
  @Output() sendStepTest = new EventEmitter();
  testCard = {
    id: 0,
    step: 0,
    title: '',
    risk: null,
    resource: null,
    day: null,
    money: null,
  };
  buyingActions: any[] = [];
  payingActions: any[] = [];
  dataResources: ActionSet[] = [];
  subActivities: Subscription;
  test = [1];
  type: string;
  tmpAction: ActionSet = new ActionSet();
  isHistory = false;
  subHistory: Subscription;
  roleID: any;
  userName: any;
  roles: any[] = [];
  myInformation: any;
  subRisks: Subscription;
  numOfRisks: any;

  constructor(private subscription: SubscriptionService,
              private nzMessage: NzMessageService,
              private lobbyService: LobbyService,
              private buyResourceService: BuyResourceService,
              private gameService: GameOnService) {
  }

  ngOnInit() {
    console.log(this.step);
    this.userName = this.lobbyService.username;
    this.roles = this.subscription.roles;
    this.myInformation = this.roles.filter(next => next.username === this.userName)[0];
    console.log(this.myInformation);
    this.numOfRisks = this.step.risks;

    this.subRisks = this.buyResourceService.risksReduced$.subscribe(data => {
      console.log(data);
      this.numOfRisks = this.numOfRisks - data;
    });
  }

  getCard(event) {
    this.testCard = event;
  }

  sendStep() {
    // this.currentStep.emit(this.step.title);
    this.sendTestClick.emit(true);
    // this.subscription.sendPayingActions(test);
    // this.sendStepTest.emit(test);
    this.sendStepTest.emit(this.step.payingActions);
    // this.subscription.sendPayingActions(this.step);
  }


  counter(num) {
    return new Array(num);
  }

  ngOnDestroy(): void {
    this.subActivities.unsubscribe();
    this.subRisks.unsubscribe();
  }

  openHistory() {
    if (this.step.histoty !== null) {
      this.isHistory = !this.isHistory;
    } else {
      this.nzMessage.create('warning', 'Aucun historique!');
    }
  }
}
