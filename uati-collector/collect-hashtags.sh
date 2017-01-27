#!/bin/bash

hashtags=(brasil brazil brasil2016 brazil2016 jogosolimpicos olimpiadas olimpiadas2016 olympics rio2016 riodejaneiro)

for hashtag in ${hashtags[@]}
do
  echo "collecting hashtag '${hashtag}'"
  curl -L "http://localhost:8080/collect?hashtag=${hashtag}"
done