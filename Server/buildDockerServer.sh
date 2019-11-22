#!/bin/bash

#Preparing environment
mvn clean install

# building the docker image
docker build -t domego/server .

printf "####################################"
printf "FINISH"
printf "####################################"
