# Domego

Domego est un jeu sérieux dans lequel chaque joueur prend le rôle d’un acteur d’une opération de construction. L’objectif est double : collectivement les joueurs doivent faire en sorte que le projet se déroule au mieux et termine dans les délais avec le coût le plus faible possible, individuellement, ils doivent assurer la pérennité de leur activité en répondant à différents objectifs (faire des bénéfices, assurer leur renommée…).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Before starting the installation, check the Frameworks and tools used (part 'Built with').

### Installing

#### Server

Before deploying D, the server and the client must be install on your computer. 
Then, build the server project like this :

```
cd Server
mvn clean install
```

If you want to deploy the app on your computer, that's  all you have to do, the app can be running at this point

But if you deploy the app in docker, you need to build the image with the following command :

```
cd Server
sh buildDockerServer.sh
```

#### Web app

Then, you have to do the same thing for the client.

```
cd Angular
npm install
```

If you don't run the client on the same computer as the server. You must change the ip adress of the server. Go to Angular\src\app\model\url.ts and change the variables. 

When it's done, if you want you can build the docker image.

```
cd Angular
sh buildDockerClient.sh
```

### Deploying

Then, to deploy on your computer, you just have to run the server and the client.

For the client, you need to use this command :

```
cd Angular
ng serve --host 0.0.0.0
```

And for the server, it is this one : 

```
cd Server
mvn spring-boot:run
```

The server needs MongoDB to work correctly, so if you don't have it, we had create a docker-compose with a mongoDB container.
To run this multi-container docker applications, just enter this command to the root of the application : 

```
docker-compose up --build
```

## Running the tests

We have developped some tests to check the behavior of the server. For run all the tests, you just have to use this command :

```
cd Server
mvn test
```

## Built With

* [Angular](https://angular.io/) - The web framework used
* [NodeJS](https://nodejs.org/) - Dependency Management for web app
* [Maven](https://maven.apache.org/) - Dependency Management for server app
* [Spring Boot](https://spring.io/) - The server framework used
* [Java 11](https://www.java.com/) - The server language and version used
* [MongoDB](https://www.mongodb.com/) - The database framework used

## Authors

* **Gilles Chandelier** - *Initial work* - [GillesChandelier](https://github.com/GillesChandelier)
* **Jiaqi Lui** - *Initial work* - [LiuJiaqi-unice](https://github.com/LiuJiaqi-unice)
* **Gaetan Vialon** - *Initial work* - [VialonGaetan](https://github.com/VialonGaetan)
* **Wei Wang** - *Initial work* - [WANGWei118](https://github.com/WANGWei118)


