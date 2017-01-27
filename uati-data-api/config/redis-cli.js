'use strict';

const redis = require('redis');
const bluebird = require('bluebird');

bluebird.promisifyAll(redis.RedisClient.prototype);
bluebird.promisifyAll(redis.Multi.prototype);

const redisCli = redis.createClient();

redisCli.on('error', (err) => {
  console.log(`Error ${err}`);
});

exports.redisCli = redisCli;
