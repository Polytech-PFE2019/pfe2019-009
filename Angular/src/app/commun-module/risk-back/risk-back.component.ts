import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Risks} from '../../model/risks';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {transition, trigger, useAnimation} from '@angular/animations';
import {flipOutX} from 'ng-animate';
import {Roles} from '../../model/roles';
import {NzNotificationService} from "ng-zorro-antd";

@Component({
  selector: 'app-risk-back',
  templateUrl: './risk-back.component.html',
  styleUrls: ['./risk-back.component.css'],
  animations: [
    trigger('flipOutX', [transition('* => *', useAnimation(flipOutX, {
      // Set the duration to 5seconds and delay to 2seconds
    }))])]
})
export class RiskBackComponent implements OnInit {
  @Input() step: number;
  @Input() myTiming: any;
  @Input() title = 0;
  @Output() sendCard = new EventEmitter();
  @Input() card: any = null;
  @Input() isDiabled = false;
  @ViewChild('money', {static: true}) money: TemplateRef<{}>;
  @ViewChild('day', {static: true}) day: TemplateRef<{}>;
  @ViewChild('risk', {static: true}) risk: TemplateRef<{}>;
  @ViewChild('resource', {static: true}) resource: TemplateRef<{}>;
  risks: any = Risks;
  riskRemainNb = 2;
  roles = Roles;
  test = {
    id: 0,
    step: 0,
    title: '',
    risk: null,
    resource: null,
    day: null,
    money: null,
  };
  flipOutX = false;
  isVanished = true;
  rolesWithPalyers: any[] = [];
  myInformation: any = null;
  extraPlayer: any = null;
  extraPayment = 0;
  activityID = 0;

  constructor(private subService: SubscriptionService,
              private notification: NzNotificationService) {
  }


  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }

  ngOnInit() {
    this.myInformation = this.subService.myRole;
    this.rolesWithPalyers = this.subService.roles;
    console.log(this.card);
    setTimeout(() => {
      for (const b of this.card.bonus) {
        switch (b.type) {
          case 'MONEY':
            const tmp1 = this.getStyleById(b.roleID);
            if (tmp1.id === this.myInformation.id) {
              this.extraPlayer = this.myInformation;
            } else {
              this.extraPlayer = tmp1;
            }
            this.extraPayment = b.amount;
            this.notification.template(this.money);
            break;
          case 'DAYS':
            this.activityID = b.activityIdAssociate;
            this.extraPayment = b.amount;
            this.notification.template(this.day);
            break;
          case 'RISK':
            this.activityID = b.activityIdAssociate;
            this.extraPayment = b.amount;
            this.notification.template(this.risk);
            break;
          case 'RESOURCES':
            const tmp4 = this.getStyleById(b.roleID);
            if (tmp4.id === this.myInformation.id) {
              this.extraPlayer = this.myInformation;
            } else {
              this.extraPlayer = tmp4;
            }
            this.extraPayment = b.amount;
            this.notification.template(this.resource);
            break;
        }
      }
    }, 2000);
    console.log(this.myTiming);
    setTimeout(() => {
      this.isVanished = false;
      this.isDiabled = true;
      console.log(this.isVanished);
    }, this.myTiming * 1000 + 1000);
  }

  getRoleById(id) {
    return this.roles.filter(next => next.id === id)[0];
  }


}
