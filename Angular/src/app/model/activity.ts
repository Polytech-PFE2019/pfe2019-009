export class Activity {
  title: string;
  numberOfDays: number;
  status: string;
  description: string;
  buyingActions: any[];
  payingActions: any[];
  risks: number;


  constructor(r) {
    this.title = 'Etape' + r.activityID;
    this.numberOfDays = r.numberOfDays;
    this.status = r.status;
    this.description = r.description;
    this.buyingActions = r.buyingActions;
    this.payingActions = r.payingActions;
    this.risks = r.risks;
  }

  addAttributes(activity) {
    this.title = 'Etape ' + activity.activityID;
    this.numberOfDays = activity.description;
    this.status = activity.status;
    this.buyingActions = activity.buyingActions;
    this.payingActions = activity.payingActions;
  }
}
