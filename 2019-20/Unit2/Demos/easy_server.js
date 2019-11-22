var express = require('express');
var app = express();
var port = 3000;

app.listen(port, function(){
  console.log('Easy server listening for requests on port'+ port+'!');
});

app.get('/', function(request, response){
  response.writeHead(200, {'Content-Type': 'text/html'})
  response.write('<HTML><BODY><H1>Hello World!</H1></BODY></HTML>');
  response.send();
});
