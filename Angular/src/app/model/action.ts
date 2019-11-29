export interface Action {
  amountToPay: number;
  bonusAmount: number;
}

export class ActionSet {
  id: number;
  basicActions: Action[];
  riskActions: Action[];
  durationActions: Action[];
}
