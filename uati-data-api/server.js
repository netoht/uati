'use strict';

const Hapi = require('hapi');

const server = new Hapi.Server();

server.connection({
  host: '0.0.0.0',
  port: parseInt(process.env.PORT, 10) || 8081,
  routes: { cors: true }
});

server.register([
  {
    register: require('./config/routes.js')
  }
], function () {

  server.start((err) => {
    if (err) throw err;
    console.log(`Server running at: ${server.info.uri}`);
  }); 

});
