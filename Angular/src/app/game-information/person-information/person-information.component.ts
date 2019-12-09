import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { BuyResourceService } from '../../service/resources/buy-resource.service';
import { SubscriptionService } from '../../service/subscriptionSerivce/subscription.service';
import { LobbyService } from '../../service/lobbyService/lobby.service';
import { GameOnService } from '../../service/gameOnService/game-on.service';
import { NzNotificationService } from 'ng-zorro-antd';

@Component({
  selector: 'app-person-information',
  templateUrl: './person-information.component.html',
  styleUrls: ['./person-information.component.css']
})
export class PersonInformationComponent implements OnInit, OnDestroy {
  playersWithRoles: any[] = [];
  subResourcesBuyed: Subscription;
  subPayment: Subscription;
  currentMonney: any;
  subReduced: Subscription;
  currentResource = 0;
  subPlayersWithRoles: Subscription;
  userName: string;
  subUserName: Subscription;
  myInformation: any;
  isPointsVistoire = false;
  isSpecial = false;
  isDescription = true;
  isPlan = false;
  subCost: Subscription;
  subDays: Subscription;
  subRisk: Subscription;
  subGame: Subscription;
  cost: any;
  day: any;
  risk: any;

  constructor(private resourceService: BuyResourceService,
    private lobbyService: LobbyService,
    private gameService: GameOnService,
    private subscription: SubscriptionService,
    private notificationService: NzNotificationService) {
  }

  ngOnInit() {
    this.userName = this.lobbyService.username;
    this.playersWithRoles = this.subscription.roles;

    console.log(this.userName);
    console.log(this.playersWithRoles);
    this.myInformation = this.playersWithRoles.filter(next => next.username === this.userName)[0];
    console.log(this.myInformation);
    this.currentMonney = this.myInformation.money;

    this.subResourcesBuyed = this.resourceService.resources$.subscribe(data => {
      console.log('resourceBuyed' + data);
      this.currentResource = data + this.currentResource;
      this.resourceService.sendCurrentResource(this.currentResource);
    });

    this.subPayment = this.resourceService.payment$.subscribe(data => {
      this.currentMonney = this.currentMonney - data;
      this.resourceService.sendCurrentMonney(this.currentMonney);
    });

    this.subReduced = this.resourceService.reduce$.subscribe(data => {
      this.currentResource = this.currentResource - data;
      this.resourceService.sendCurrentResource(this.currentResource);
    });

    this.subPlayersWithRoles = this.subscription.playersWithRoles$.subscribe(data => {
      console.log(data);
      // this.playersWithRoles = data;
    });

    this.subRisk = this.subscription.failures$.subscribe(data => {
      this.risk = data;
    });

    this.subDays = this.subscription.days$.subscribe(data => {
      this.day = data;
    });

    this.subCost = this.subscription.costs$.subscribe(data => {
      this.cost = data;
    });

    console.log(this.lobbyService.username);

    //
    // this.subUserName = this.subscription.userName$.subscribe(data => {
    //   console.log(data);
    //   this.userName = data;
    // });
    this.subGame = this.gameService.reponses$.subscribe(data => {
      if (data.response === 'drawRisk') {
        this.currentMonney = data.player.money;
        this.currentResource = data.player.resources;
      }
      if (data.response === "PAY_CONTRACT") {
        this.currentMonney = data.money;
        this.notificationService.blank("Paiement", 'Une partie du contrat a été payée : ' + data.giverRoleName + 'a payé ' + data.amount + 'k au' + data.receiverRoleName)
      }
    });
  }

  ngOnDestroy(): void {
    this.subResourcesBuyed.unsubscribe();
    this.subPayment.unsubscribe();
    this.subReduced.unsubscribe();
    this.subPlayersWithRoles.unsubscribe();
    this.subUserName.unsubscribe();
    this.subCost.unsubscribe();
    this.subDays.unsubscribe();
    this.subRisk.unsubscribe();
    this.subGame.unsubscribe();
  }

  showPointsVistoire() {
    this.isPointsVistoire = true;
    this.isSpecial = false;
    this.isDescription = false;
    this.isPlan = false;
  }

  showSpecial() {
    this.isSpecial = true;
    this.isPointsVistoire = false;
    this.isDescription = false;
    this.isPlan = false;
  }

  showDescription() {
    this.isDescription = true;
    this.isPointsVistoire = false;
    this.isSpecial = false;
    this.isPlan = false;
  }

  showPlan() {
    this.isPlan = true;
    this.isDescription = false;
    this.isPointsVistoire = false;
    this.isSpecial = false;
  }
}
