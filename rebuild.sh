#!/bin/bash

IMAGE_NAME=wikidle
CONTAINER_NAME=wikidle

docker build -t $IMAGE_NAME .
docker rm -f $CONTAINER_NAME 2>/dev/null || true
docker run -d --name $CONTAINER_NAME -p 8080:8080 $IMAGE_NAME
read