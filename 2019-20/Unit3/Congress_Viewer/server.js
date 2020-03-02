var express = require('express');
var fs = require('fs');
var favicon = require('serve-favicon');
var app = express();
var methodOverride = require('method-override');
const apirequest = require('request');


app.use(methodOverride('_method'));
app.use(express.urlencoded());

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

var politicians = [];

app.get('/view', function(request, responsea){
  var options = {
    url: "https://api.propublica.org/congress/v1/116/senate/members.json",
    json: true,
    headers: {
      "X-API-Key": "wvz6nlmPtKUxSUTEDqeDaJpO1Wkv8jC6zpEQZups"
    }
  }
  var options2 = {
    url: "https://api.propublica.org/congress/v1/116/house/members.json",
    json: true,
    headers: {
      "X-API-Key": "wvz6nlmPtKUxSUTEDqeDaJpO1Wkv8jC6zpEQZups"
    }
  }

  apirequest.get(options, function(error, response, body) {
    console.error('error:', error); // Print the error if one occurred
    console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
    //console.log('body:', body.results[0].members); // Print the HTML for the Google homepage.
    politicians[0] = (body.results[0]);
  });
  apirequest.get(options2, function(error, response, body) {
    console.error('error:', error); // Print the error if one occurred
    console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
  //  console.log('body:', body.results[0].members); // Print the HTML for the Google homepage.
    politicians[1] = (body.results[0]);
  });
  responsea.status(200);
  responsea.setHeader('Content-Type', 'text/html')
  responsea.render('politicians',{feedback:politicians});
});

app.get('/logout', function(request, response){
  response.status(200);
  response.setHeader('Content-Type', 'text/html')
  response.render('index',{feedback:""});
});

app.get('/about', function(request, response){
  response.status(200);
  response.setHeader('Content-Type', 'text/html')
  response.render('about',{feedback:""});
});
