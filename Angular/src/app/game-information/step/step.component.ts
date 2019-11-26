import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.css']
})
export class StepComponent implements OnInit {
  @Input() step: any = null;
  @Output() currentStep = new EventEmitter();
  datasets = [1];
  numOfRisks = 4;
  activityRisk = [{
    monney: 0,
    risk: 0,
  },
    {
      monney: 2,
      risk: 1
    },
    {
      monney: 4,
      risk: 2
    }
  ];

  activityDuration = [{
    monney: 0,
    duration: 0,
  },
    {
      monney: 2,
      duration: 1
    },
    {
      monney: 4,
      duration: 2
    }
  ];
  constructor() {
  }

  ngOnInit() {
  }


  sendStep() {
    this.currentStep.emit(this.step.title);
  }


  counter(num) {
    return new Array(num);
  }
}
