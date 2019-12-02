export class Risk {
  id: number;
  title: string;
  step: number;
  resource: any;
  day: any;
  money: any;

  constructor(r: any) {
    this.id = r.id;
    this.title = r.title;
    this.step = r.step;
    this.resource = '';
    this.day = '';
    this.money = '';
  }
}
