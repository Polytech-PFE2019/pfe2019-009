import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, AfterViewInit} from '@angular/core';
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
export class StepComponent implements OnInit, OnDestroy, AfterViewInit {
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
  test = [1];
  type: string;
  tmpAction: ActionSet = new ActionSet();
  isHistory = false;
  roleID: any;
  userName: any;
  roles: any[] = [];
  myInformation: any;
  numOfRisks: any;
  subCurrentActivityID: Subscription;

  CURRENT_COLOR = 'grey';
  PREVIOUS_COLOR = 'lightgrey';
  numOfStep = 1;
  isDone = false;

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

    this.subCurrentActivityID = this.subscription.currentActivityID$.subscribe(data => {
      console.log(data, this.step.title);
      this.numOfStep += 1;
      if (data === this.step.title) {
        document.getElementById('stepCard' + this.step.title).style.border = 'solid 8px #82ce2c';
      } else if (data > this.step.title) {
        this.isDone = true;
        document.getElementById('stepCard' + this.step.title).style.border = 'solid 8px #123074';
        document.getElementById('stepCard' + this.step.title).style.backgroundColor = '#aaaaaa38';

      } else if (data < this.step.title) {
        document.getElementById('stepCard' + this.step.title).style.border = 'solid 2px #f68c12';
      }
    });

  }

  ngAfterViewInit() {
    if (this.step.title === 1) {
      document.getElementById('stepCard' + this.step.title).style.border = 'solid 8px #82ce2c';
    } else {
      document.getElementById('stepCard' + this.step.title).style.border = 'solid 2px #f68c12';
    }
  }

  getCard(event) {
    this.testCard = event;
  }

  sendStep() {
    this.sendTestClick.emit(true);
    this.sendStepTest.emit(this.step.payingActions);
  }


  counter(num) {
    if (num >= 0) {
      return new Array(num);
    }
  }

  ngOnDestroy(): void {
    this.subCurrentActivityID.unsubscribe();
  }

  openHistory() {
    if (this.step.histoty !== null) {
      this.isHistory = !this.isHistory;
    } else {
      this.nzMessage.create('warning', 'Aucun historique!');
    }
  }
}
