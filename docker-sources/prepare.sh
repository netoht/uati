#!/bin/bash

set -eux

target=./app/

rm -rf $target
mkdir $target
cp -a ../docker/script.cql $target
cp -a ../spark-hadoop/spark-2.1.0-bin-hadoop2.7 $target
cp -a ../uati-aggregator/build/libs/uati-aggregator-all.jar $target
cp -a ../uati-collector/build/libs/uati-collector-1.0.0-SNAPSHOT.jar $target
cp -a ../uati-collector/collect-hashtags.sh $target
cp -a ../uati-data-api $target
cp -a ../uati-web-ui $target
