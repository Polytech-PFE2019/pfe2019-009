#!/bin/bash

# building the docker image
docker build -t domego/client .

printf "####################################"
printf "FINISH CLIENT BUILD"
printf "####################################"
