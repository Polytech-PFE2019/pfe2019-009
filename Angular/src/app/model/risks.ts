export const Risks = [
  {
    step: 1,
    id: 1,
    title: 'Manque de ressources humaines internes',
    risk: null,
    day: null,
    money: null,
    resource: ['blue', 2, 1], // blue pay 2 more resources at 1st step
  },
  {
    step: 1,
    id: 2,
    title: 'Chef de projet inexpérimenté',
    risk: [1, 2], // 1 more risk at 2nd step
    day: null,
    money: null,
    resource: null,
  },
  {
    step: 1,
    id: 3,
    title: 'Chef de projet maitrisant mal les aspects administratifs',
    risk: [1, 3], // 1 more risk at 3nd step
    day: null,
    money: null,
    resource: null,
  },
  {
    step: 1,
    id: 4,
    title: 'Chef de projet trop perfectionniste',
    risk: null,
    day: [10, 12], // 10 more days at 12th step
    money: null,
    resource: null,
  },
  {
    step: 1,
    id: 5,
    title: 'Appui financier de l’Etat',
    risk: null,
    day: null,
    money: ['blue', 5], // blue get 5k
    resource: null,
  },
];
