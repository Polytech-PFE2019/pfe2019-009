import {Component, Input, OnInit} from '@angular/core';
import {Roles} from "../model/roles";
import {of} from "rxjs";

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  @Input() pdv = [];
  @Input() detail = [];
  // @Input() projectInfo = [];
  projectInfo ={
    duration: 365,
    failure: 100,
    cost: 150,
  };

  data: any[] = [];
  constructor() { }

  ngOnInit() {
    this.loadData();
  }

  loadData(): void {
    this.data = [
      {
        title: 'Maitre d\'ouvrage',
        src: '../../assets/Maitre d’ouvrage.jpg',
        point: 20,
        detail:'',
      },
      {
        title: 'Maitre d\'œuvre (Architecte)',
        src: '../../assets/Architecte.png',
        point: 0,
        detail:'',
      },
      {
        title: 'Bureau d\'étude',
        src: '../../assets/Bureau d\'etude.png',
        point: 10,
        detail:'',
      },
      {
        title: 'Bureau de contrôle',
        src: '../../assets/Bureau de contrôle.jpg',
        point: -10,
        detail:'',
      },
      {
        title: 'Entreprise Corps Etat Secondaire',
        src: '../../assets/Entreprise Corps Etat Secondaire.png',
        point: 0,
        detail:'',
      },
      {
        title: 'Entreprise Gros Œuvre',
        src: '../../assets/Entreprise Gros Œuvre.png',
        point: 20,
        detail:'',
      }
    ];
  }


}
