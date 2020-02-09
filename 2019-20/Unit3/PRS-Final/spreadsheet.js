var GoogleSpreadsheet = require('google-spreadsheet');

var creds = require('./models/client_secret.json');

// Create a document object using the ID of the spreadsheet - obtained from its URL.

var doc = new GoogleSpreadsheet('1n8RUku2I9IQhmLshpn1usoe_ZNcX8IGg9ILOedub7CY');

// Authenticate with the Google Spreadsheets API.

doc.useServiceAccountAuth(creds, function (err) {

  // Get all of the rows from the spreadsheet.

  doc.getRows(1, function (err, rows) {

    console.log(rows);

  });

});
