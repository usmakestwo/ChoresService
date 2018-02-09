#!/bin/bash

# Purpose: Build Chore Service.
#
# Author: Dinesh Alapati, dine.alapati@gmail.com
#
# Parameters:
# $1: Version of Chore Service..
#

xVERSION=$1
xDOCKER_IMAGE_NAME=usmakestwo/chore-service

if [ -z $1 ]; then
   echo "Missing version"
   exit 1
fi

function dockerImageNotExists() {
    xDOCKERIMAGE=$(docker images | grep $xDOCKER_IMAGE_NAME)
    if [ ! -z "$xDOCKERIMAGE" ] ; then
        return 1
    else
        return 0
    fi
}

echo "Building Chore Service..."

if dockerImageNotExists; then
    echo "$xDOCKER_IMAGE_NAME image not found. Pulling from docker hub.."
    docker pull $xDOCKER_IMAGE_NAME:latest
fi

echo "Running maven build.."
mvn clean install
echo "Building Docker image with version $xVERSION"
docker build -t $xDOCKER_IMAGE_NAME:$xVERSION -t $xDOCKER_IMAGE_NAME:latest . --no-cache=true
echo "Imaging built with version $xVERSION"

echo "Pushing image to DockerHub"
docker push $xDOCKER_IMAGE_NAME
echo "Image successfully published!"