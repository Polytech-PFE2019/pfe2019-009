export class History {
  activityID: number;
  roleID: any;
  payments: any[];


  constructor(id, roleID) {
    this.activityID = id;
    this.payments = [];
    this.roleID = roleID;
  }
}
