const request = require('request');
/*
request.get('http://www.example.com', function(error, response, body) {
  console.error('error:', error); // Print the error if one occurred
  console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
  console.log('body:', body); // Print the HTML for the Google homepage.
});
*/
var options = {
  url: "https://api.propublica.org/congress/v1/116/senate/members.json",
  json: true,
  headers: {
    "X-API-Key": "wvz6nlmPtKUxSUTEDqeDaJpO1Wkv8jC6zpEQZups"
  }
}

request.get(options, function(error, response, body) {
  console.error('error:', error); // Print the error if one occurred
  console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
  console.log('body:', body.results[0].members); // Print the HTML for the Google homepage.
});
