$(document).ready(function() {
  $("#my_button").click(function(event) {

    var apiKey = 'wvz6nlmPtKUxSUTEDqeDaJpO1Wkv8jC6zpEQZups';
    var requestURL = 'https://api.propublica.org/congress/v1/bills/search.json?query=sanders';

    $.ajax({
      url: requestURL,
      type: "GET",
      dataType: 'json',
      headers: {
        'X-API-Key': apiKey
      }
    }).done(function(data) {
      var content;
      for (var i = 0; i < data.results[0].bills.length; i++) {
        var bill = data.results[0].bills[i];
        content += "<h1>" + bill.short_title + "</h1>";
        content += "<h3>" + bill.title + "</h3>"
      }
      $('#bills').html(content);
      console.log(data)
    });
  });
});
