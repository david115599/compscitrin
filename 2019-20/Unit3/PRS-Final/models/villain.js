var fs = require("fs");
var GoogleSpreadsheet = require('google-spreadsheet');
var creds = require('./client_secret.json');
var doc = new GoogleSpreadsheet('1n8RUku2I9IQhmLshpn1usoe_ZNcX8IGg9ILOedub7CY');

exports.getVillain=function(villain_id, callback){
  console.log("Villains.getVillain: "+villain_id);
  var villain = createBlankVillain();

  getAllDatabaseRows(function(v){
    for(var i=0; i<v.length; i++){
      if(v[i].name==villain_id) {
        villain.name=v[i].name;
        villain.gameswon=v[i].gameswon;
        villain.gameslost=v[i].gameslost;
        villain.strategy=v[i].strategy;
      }
    }
    callback(villain);
  });
}

exports.updateVillain=function(villain_id, new_info, callback) {
  console.log("Villains.updateVillain: "+villain_id);
  exports.getVillain(villain_id,function(v){
    v[new_info]++;

    callback(v);
  });

}

exports.villainHand=function(villain_id,player,callback){
  exports.getVillain(villain_id,function(villain){
    console.log("strategy="+villain.strategy);
    var v_hand = " "
    if(villain["strategy"]=="Random"){
      console.log("found strategy");
      var r = Math.random();
      if(r<=.33)v_hand="paper";
      else if(r<=.66)v_hand="rock";
      else v_hand="scissors";
    }
    else if(villain["strategy"]=="Cheater"){
      if(user_data["weapon"]=="rock")v_hand="paper";
      else if(user_data["weapon"]=="scissors")v_hand="rock";
      else v_hand="scissors";
    }
    else if(villain["strategy"]=="Win Stats"){
      if(Math.max(player[5],player[6],player[7])==player[5])v_hand="scissors";
      else if(Math.max(player[5],player[6],player[7])==player[6])v_hand="paper";
      else if(Math.max(player[5],player[6],player[7])==player[7])v_hand="rock";
    }
    else if(villain["strategy"]=="Lose Stats"){
      if(Math.max(player[5],player[6],player[7])==player[5])v_hand="rock";
      else if(Math.max(player[5],player[6],player[7])==player[6])v_hand="scissors";
      else if(Math.max(player[5],player[6],player[7])==player[7])v_hand="paper";
    }
    else if(villain["strategy"]=="Tie Stats"){
      if(Math.max(player[5],player[6],player[7])==player[5])v_hand="paper";
      else if(Math.max(player[5],player[6],player[7])==player[6])v_hand="rock";
      else if(Math.max(player[5],player[6],player[7])==player[7])v_hand="scissors";
    }
    else if(villain["strategy"]=="No Ties"){
      var r = Math.random();
      var a = ["paper","rock","scissors"];
      a.splice(a.indexOf(user_data["weapon"]),1);
      if(r<.5) v_hand=a[0];
      else v_hand=a[1];
    }
    else if(villain["strategy"]=="Favors Scissors"){
      var r = Math.random();
      if(r<.5) v_hand="scissors";
      else if(r<.75) v_hand="rock";
      else v_hand="paper";
    }
    else if(villain["strategy"]=="Favors Paper"){
      var r = Math.random();
      if(r<.9) v_hand="paper";
      else if(r<.95) v_hand="rock";
      else v_hand="scissors";
    }
    callback(v_hand);
  });
}

exports.allVillains=function(callback){
  console.log("Villains.allVillains");
  var villain_data=[];
  var villain = createBlankVillain();
  getAllDatabaseRows(function(rows){
    for(var i=0; i<rows.length; i++){
      villain={
        name:rows[i].name,
        gamesplayed:rows[i].gamesplayed,
        gameswon:rows[i].gameswon,
        gameslost:rows[i].gameslost,
      }
      villain_data.push(villain);
    }
    callback(villain_data);
  });
}

var createBlankVillain= function(){
  var villain={
    name:"test",
    games_played:"test",
    lost:"test",
    won:"test",
    strategy:"test"
  };
  return villain;
}

var getAllDatabaseRows= function(callback){
  //return fs.readFileSync(__dirname +'/../data/users.csv', 'utf8').split('\n');
  doc.useServiceAccountAuth(creds, function (err) {
    // Get all of the rows from the spreadsheet.
    doc.getRows(2, function (err, rows) {
      callback(rows);
    });
  });
}
