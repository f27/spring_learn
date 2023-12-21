#!/bin/bash
echo '### Java version ###'
java --version

echo '### Pulling postgres image ###'
docker pull postgres:15.1

echo '### Show images ###'
docker images

echo '### Create volume for postgres ###'
docker volume create pgdata_bobr

echo '### Stop and remove running containers ###'
docker stop "$(docker ps -a -q)"
docker rm "$(docker ps -a -q)"

echo '### Run postgres db ###'
docker run --name bobr-all-db -p 5432:5432 -e POSTGRES_PASSWORD=secret -v pgdata_bobr:/var/lib/postgresql/data -d postgres:15.1

echo '### Show running containers ###'
docker ps