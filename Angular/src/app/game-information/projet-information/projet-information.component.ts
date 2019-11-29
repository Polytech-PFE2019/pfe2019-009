import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-projet-information',
  templateUrl: './projet-information.component.html',
  styleUrls: ['./projet-information.component.css']
})
export class ProjetInformationComponent implements OnInit {
  tabs = [
    {
      active: true,
      name: 'La durée',
      icon: 'calendar',
      id: 1
    },
    {
      active: false,
      name: 'La defaillance',
      icon: 'warning',
      id: 2
    },
    {
      active: false,
      name: 'Le cout',
      icon: 'money-collect',
      id: 3
    },
  ];
  constructor() { }

  ngOnInit() {
  }

}
