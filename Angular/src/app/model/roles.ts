export const Roles = [
  {
    title: 'Maître d\'ouvrage',
    id: 1,
    description: 'Le maître d’ouvrage est la personne pour ' +
      'laquelle est réalisée l’ouvrage. ' +
      'Le maître d\'ouvrage est le porteur du projet,\n' +
      'il définit l\'objectif du projet, son calendrier et le budget.' +
      ' Le résultat attendu du projet est ' +
      'la réalisation et la livraison d\'un résultat,\n' +
      'appelé l\'ouvrage.',
    mission: {
      pointsDeVistore:
        [
          '+1/j d’avance',
          '+1/k économisé',
          '+1/défaillance évitée'
        ],
      specialActivity:
        [
          'Budget projet : 135k',
          'Délai : 380 j',
          'Défaillance : 20'
        ]
    },
    src: '../../assets/Maitre d’ouvrage.jpg',
    profile: '../../assets/profiles/1.jpeg',
    money: 150,
    style: {
      background: '#2f63d4',
      height: '30px',
      width: '30px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      color: 'white',
    }
  },
  {
    title: 'Maitre d\'œuvre (Architecte) ',
    id: 2,
    description: 'Le maitre d’œuvre (souvent l’architecte)' +
      ' est le chef de projet de construction,' +
      ' la personne physique ou morale qui dirige ' +
      'et vérifie la bonne exécution des travaux. ' +
      'En tant qu’architecte, il doit aussi concevoir' +
      ' le projet en répondant aux exigences du maitre d’ouvrage.',
    mission: {
      pointsDeVistore:
        [
          '+5 si resp. bud. & temps',
          '+1/défaillance évitée',
          '+1/k gagné (bénéfice)'
        ],
      specialActivity:
        [
          'Objectif négociation : Recevoir entre 80k et 115k',
        ]
    },
    src: '../../assets/Architecte.png',
    profile: '../../assets/profiles/2.jpg',
    money: 30,
    style: {
      background: '#4cc916',
      height: '30px',
      width: '30px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      color: 'white',
    }
  },
  {
    title: 'Bureau d\'études',
    id: 3,
    description: 'Le bureau d’études doit assister l’architecte sur ' +
      'les spécificités techniques qui relèvent de sa compétence.' +
      ' Ils assurent des études techniques spécifiques : ' +
      'étudedelastructure, étude de sol, étude thermique,' +
      ' étude acoustique, étude des réseaux ...',
    mission: {
      pointsDeVistore:
        [
          '+2/k gagné (bénéfice)',
          '+2 si respect délai projet',
          '+2 si respect défaillance projet'
        ],
      specialActivity:
        [
          'Objectif négociation : Recevoir entre 10k et 20k',
        ]
    },
    src: '../../assets/Bureau d\'etude.png',
    profile: '../../assets/profiles/4.jpeg',
    money: 20,
    style: {
      background: '#f7e80c',
      height: '30px',
      width: '30px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      color: 'white',
    }
  },
  {
    title: 'Bureau de contrôle',
    id: 4,
    description: 'Le bureau de contrôle juge de' +
      ' la solidité de l\'ouvrage, ' +
      'et vérifie le respect des normes et' +
      ' des règles de construction -' +
      ' appelées souvent « règles de l\'art ». ' +
      'Il a une responsabilité juridique vis à vis ' +
      'du respects des différentes normesetréglementation.',
    mission: {
      pointsDeVistore:
        [
          '+1/k gagné (bénéfice)',
          '+1 si respect délai projet',
          '+5 si respect défaillance projet'
        ],
      specialActivity:
        [
          'Objectif négociation : Recevoir entre 10k et 25k',
        ]
    },
    src: '../../assets/Bureau de contrôle.jpg',
    profile: '../../assets/profiles/7.jpg',
    money: 20,
    style: {
      background: '#000000',
      height: '30px',
      width: '30px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      color: 'white',
    }
  },
  {
    title: 'Entreprise Corps\tEtat Secondaire',
    id: 6,
    description: 'Leur rôle est de construire tout ce qui ' +
      'n’est pas assuré par le gros œuvre. ' +
      'Il s’agit des cloisons et plâtrerie, de la peinture,' +
      ' de l’électricité, de la ventilation, des menuiseries, ' +
      'des revêtement de sol,de la plomberie …',
    mission: {
      pointsDeVistore:
        [
          '+2/k gagné (bénéfice)',
          '+2 si respect délai projet',
          '+2 si respect défaillance projet'
        ],
      specialActivity:
        [
          'Objectif négociation : Recevoir entre 20k et 40k',
        ]
    },
    src: '../../assets/Entreprise Corps Etat Secondaire.png',
    profile: '../../assets/profiles/8.jpg',
    money: 30,
    style: {
      background: '#cc00b4',
      height: '30px',
      width: '30px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      color: 'white',
    }
  },
  {
    title: 'Entreprise Gros Œuvre',
    id: 5,
    description: 'Ces entreprises ont pour but de bâtir' +
      ' l’ossature de l’ouvrage. Cela comprend les fondations, ' +
      'les poutres, les poteaux, ' +
      'les murs, la charpente, le dallage … ' +
      'Ils ont aussi souvent en charge les installations ' +
      'de chantier et le terrassement.',
    mission: {
      pointsDeVistore:
        [
          '+2/k gagné (bénéfice)',
          '+2 si respect délai projet',
          '+2 si respect défaillance projet'
        ],
      specialActivity:
        [
          'Objectif négociation : Recevoir entre 25k et 50k',
        ]
    },
    src: '../../assets/Entreprise Gros Œuvre.png',
    profile: '../../assets/profiles/grilla.jpg',
    money: 30,
    style: {
      background: '#ff0000',
      height: '30px',
      width: '30px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      color: 'white',
    }
  },

];
