'use strict';

const Path = require('path');
const Hapi = require('hapi');
const Hoek = require('hoek');
const axios = require('axios');

const server = new Hapi.Server();

server.connection({
  host: '0.0.0.0',
  port: parseInt(process.env.PORT, 10) || 3000,
});

server.register([require('vision'), require('inert')], (err) => {

  Hoek.assert(!err, err);

  server.views({
    engines: {
      html: require('handlebars')
    },
    relativeTo: __dirname,
    path: 'templates'
  });

  server.route({
    method: 'GET',
    path: '/',
    handler: function (request, reply) {
      reply.view('index');
    }
  });

  server.route({
    method: 'GET',
    path: '/public/{param*}',
    handler: {
      directory: {
        path: 'public'
      }
    }
  });

  server.start((err) => {
    if (err) throw err;
    console.log(`Server running at: ${server.info.uri}`);
  });
});