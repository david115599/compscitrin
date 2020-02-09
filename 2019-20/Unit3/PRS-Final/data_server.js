var express = require('express');
var fs = require('fs');
var favicon = require('serve-favicon');
var app = express();
var methodOverride = require('method-override');

app.use(methodOverride('_method'));
app.use(express.urlencoded());

//MVC connection
app.use(require('./controllers/user'));

app.use(express.static('public'));
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.use(favicon(__dirname + '/public/images/logo.png'));

var port = process.env.PORT || 3000; //||8000
app.listen(port, function(){
  console.log('Easy server listening for requests on port '+ port+'!');
});

app.get('/', function(request, response){
  response.status(200);
  response.setHeader('Content-Type', 'text/html')
  response.render('index',{feedback:""});
});

app.get('/logout', function(request, response){
  response.status(200);
  response.setHeader('Content-Type', 'text/html')
  response.render('index',{feedback:""});
});

app.get('/rules', function(request, response){
  response.status(200);
  response.setHeader('Content-Type', 'text/html')
  response.render('rules');
});

app.get('/about', function(request, response){
  var this_user={
      name: request.params.user,
  }
  response.status(200);
  response.setHeader('Content-Type', 'text/html')
  response.render('about',{user:this_user});
});
