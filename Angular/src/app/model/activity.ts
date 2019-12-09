export class Activity {
  title: string;
  numberOfDays: number;
  status: string;
  description: string;
  buyingActions: any[];
  payingActions: any[];
  negotiationActions: any[];
  risks: number;
  rolesID: any[];
  history: any[];
  riskCards: any;
  extraPayment: any[];
  payContractAction: any[];


  constructor(r) {
    this.title = r.activityID;
    this.numberOfDays = r.numberOfDays;
    this.status = r.status;
    this.description = r.description;
    this.buyingActions = r.buyingActions;
    this.payingActions = r.payingActions;
    this.negotiationActions = r.negotiationActions;
    this.risks = r.risks;
    this.rolesID = r.rolesID;
    this.history = [];
    this.riskCards = null;
    this.extraPayment = [];
    this.payContractAction = r.payContractAction;
  }

  addAttributes(activity) {
    this.title = activity.activityID;
    this.numberOfDays = activity.description;
    this.status = activity.status;
    this.buyingActions = activity.buyingActions;
    this.payingActions = activity.payingActions;
  }
}
