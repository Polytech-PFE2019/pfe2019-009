export class Role {
  id: number;
  title: string;
  detail: string;
  src: string;
  description: string;
  mission: string;
  username: string;
  ready: boolean;
  choosed: boolean;
  profile: string;


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
  }

  addAttribute(o: any) {
    this.username = o.username;
    this.ready = o.ready;
    if (o.roleID === this.id) {
      this.choosed = true;
    } else {
      this.choosed = false;
    }
  }
}
