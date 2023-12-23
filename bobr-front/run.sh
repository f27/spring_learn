#!/bin/bash

docker build --build-arg NPM_COMMAND="build:dev" -t front:latest .
docker run -dp 80:80 front:latest -name bobr-front