#!/bin/bash

cassandra -f &
while ! cqlsh -f /app/script.cql &> /dev/null ; do
  echo -ne 'waiting for Cassandra...'\\r; sleep 2;
done

redis-server &

nohup java -jar /app/uati-collector-1.0.0-SNAPSHOT.jar &
while ! curl -s "http://localhost:8080" &> /dev/null; do 
  echo -ne "waiting for collector..."\\r; sleep 2;
done
/app/collect-hashtags.sh

/app/spark-2.1.0-bin-hadoop2.7/bin/spark-submit --class io.github.netoht.uati.aggregator.UatiAggregatorApplication /app/uati-aggregator-all.jar &

while ! nc -z localhost 6379 &> /dev/null ; do
  echo -ne "waiting for Redis..."\\r; sleep 2;
done

cd /app/uati-data-api; yarn start &

cd /app/uati-web-ui; yarn start
