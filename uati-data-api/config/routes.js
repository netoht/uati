'use strict';

var redisCli = require('./redis-cli').redisCli;

exports.register = function (server, options, next) {

  server.route({
    method: 'GET', path: '/top_twitter_profile',
    handler: (request, reply) => {
      redisCli.getAsync('top_twitter_profile').then(data => {
        if(!data) data = []
        reply(data)
        .type('application/json');
      });
    }
  });

  server.route({
    method: 'GET', path: '/hashtag_pt',
    handler: (request, reply) => {
      redisCli.getAsync('hashtag_pt').then(data => {
        if(!data) data = []
        reply(data)
        .type('application/json');
      });
    }
  });

  
  server.route({
    method: 'GET', path: '/tweet_by_hour',    
    handler: (request, reply) => {
      redisCli.getAsync('tweet_by_hour').then(data => {
        if(!data) data = []
        reply(data)
        .type('application/json');
      });
    }
  });

  next();
}

exports.register.attributes = {
  name: 'routes'
};
