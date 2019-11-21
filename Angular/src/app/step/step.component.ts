import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.css']
})
export class StepComponent implements OnInit {
  @Input() step: any = null;
  @Output() currentStep = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }


  sendStep() {
    this.currentStep.emit(this.step.title);
  }
}
