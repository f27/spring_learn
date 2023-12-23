#!/bin/bash

docker build --build-arg NPM_COMMAND="build:dev" -t front:1.0.0 -t front:latest .
docker run --name front -dp 3000:80 front:latest