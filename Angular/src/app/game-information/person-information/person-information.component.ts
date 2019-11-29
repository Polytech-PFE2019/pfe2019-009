import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Subscription} from 'rxjs';
import {BuyResourceService} from '../../service/resources/buy-resource.service';

@Component({
  selector: 'app-person-information',
  templateUrl: './person-information.component.html',
  styleUrls: ['./person-information.component.css']
})
export class PersonInformationComponent implements OnInit, OnDestroy {
  subResourcesBuyed: Subscription;
  subPayment: Subscription;
  currentMonney = 30;
  subReduced: Subscription;
  currentResource = 0;

  constructor(private resourceService: BuyResourceService) {
  }

  ngOnInit() {
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
  }

  ngOnDestroy(): void {
    this.subResourcesBuyed.unsubscribe();
    this.subPayment.unsubscribe();
  }
}
