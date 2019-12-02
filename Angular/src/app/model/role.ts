export class Role {
  id: number;
  title: string;
  detail: string;
  src: string;
  description: string;
  mission: any;
  username: string;
  ready: boolean;
  choosed: boolean;
  profile: string;
  style: any;
  money: any;


  constructor(r: any) {
    this.id = r.id;
    this.title = r.title;
    this.detail = r.detail;
    this.src = r.src;
    this.description = r.description;
    this.mission = r.mission;
    this.choosed = false;
    this.ready = false;
    this.username = '';
    this.profile = r.profile;
    this.style = r.style;
    this.money = r.money;
  }

  addThisAttributes(o: any) {
    this.username = o.username;
    this.ready = o.ready;
    this.choosed = o.roleID === this.id;
  }

  removeAttribute() {
    this.ready = false;
    this.choosed = false;
    this.username = '';
  }
}
