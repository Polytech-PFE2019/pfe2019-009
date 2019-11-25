import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  checked1 = true;
  checked2 = false;
  checked3 = false;
  constructor() { }

  ngOnInit() {
  }

}
