var express = require('express');
var router = express.Router();

var Users = require('../models/user');
var Villains = require('../models/villain');

router.get('/user/new', function(req, res){
  console.log("Request-get /user/new");

  res.status(200);
  res.setHeader('Content-Type', 'text/html');
  res.render('user_details',{user:null,feedback:null});
});

router.post('/users', function(req, res){
  console.log("Request-post /users");
  //create user with info from body
  var u = {
    name: req.body.username.trim(),
    password: req.body.password.trim(),
    firstname: req.body.firstname.trim(),
    lastname: req.body.lastname.trim()
  };

  Users.createUser(u, function(created,message){
    if(created){
      res.status(200);
      res.setHeader('Content-Type', 'text/html');
      res.render('index',{feedback:message});
    }
    else{
      res.status(200);
      res.setHeader('Content-Type', 'text/html');
      res.render('user_details',{user:null,feedback:message});
    }
  });
});

router.get('/users/:id/edit', function(req, res){
  console.log('Request-get /users/'+req.params.id+'/edit');

  Users.getUser(req.params.id, function(u){
    res.status(200);
    res.setHeader('Content-Type', 'text/html');
    res.render('user_details', {user:u,feedback:null});
  });
});

router.put('/users/:id', function(req, res){
  console.log('Request-put /user/'+req.params.id);

  var u = {
    name: req.body.username.trim(),
    password: req.body.password.trim(),
    firstname: req.body.firstname.trim(),
    lastname: req.body.lastname.trim()
  };

  Users.updateUser(req.params.id,u,"put",function(u,message){
    console.log("date updated:"+u.dateupdated);
    res.status(200);
    res.setHeader('Content-Type', 'text/html');
    res.render('user_details', {user:u,feedback:message});
  });
});

router.delete('/users/:id', function(req, res){
  console.log('Request-delete /user/'+req.params.id);

  Users.deleteUser(req.params.id,function(){
    res.status(200);
    res.setHeader('Content-Type', 'text/html');
    res.render('index',{feedback:"Account deleted"});
  });
});

router.get('/users/game', function(req, res){
  console.log('Request-get /users/game?user_name='+req.query.user_name+"&password="+req.query.password);
  Users.getUser(req.query.user_name, function(u){
    if(u.name=="test"){
      res.status(200);
      res.setHeader('Content-Type', 'text/html');
      res.render('index',{feedback:"Please enter a valid username"});
    }
    else if(u.password!=req.query.password){
      res.status(200);
      res.setHeader('Content-Type', 'text/html');
      res.render('index',{feedback:"Username and password don't match"});
    }
    else{
      res.status(200);
      res.setHeader('Content-Type', 'text/html');
      res.render('game',{user:u});
    }
  });
});

router.get('/user/:id/results', function(req,res){
  console.log("Request-get /user/"+req.params.id+'/results');

  var data={
    name: req.params.id,
    weapon: req.query.weapon,
    villain: req.query.villain_name
  }

  if(data["villain"]==" " || data["weapon"]==" "){
    response.status(200);
    response.setHeader('Content-Type', 'text/html')
    response.render('game', {user:user_data});
  }
  else{
    Users.getUser(req.params.id,function(u){
      //update games played
      Users.updateUser(req.params.id,"gamesplayed","game",function(u){
        //update rock/paper/scissors
        Users.updateUser(req.params.id,req.query.weapon,"game",function(u){
          Villains.villainHand(req.query.villain_name,u,function(v_hand){
            //file name of villain hand
            console.log("data[villain]="+data.villain);
            data["villain image"]=data["villain"]+"_"+v_hand+".svg";

            //text results of game
            if(data["weapon"]==v_hand) data["result"]="tied."
            else if(data["weapon"]=="paper"&&v_hand=="rock")data["result"]="won!"
            else if(data["weapon"]=="rock"&&v_hand=="scissors")data["result"]="won!"
            else if(data["weapon"]=="scissors"&&v_hand=="paper")data["result"]="won!"
            else data["result"]="lost."

            res.status(200);
            res.setHeader('Content-Type', 'text/html');
            res.render('results', {user:data});

            //update villain stats
            Villains.updateVillain(data.villain,data.result,function(v){
            });

            //update player win/loss/tie
            Users.updateUser(data.name,data.result,"game",function(u){
            });
          });//end Villains callback
        });//end user update callback
      });//end second user updatecallback
    });//end get user callback
  }//end else
});

router.get('/stats', function(request, response){
  console.log("Request-get /stats");
  Users.allUsers(function(users_data){
    Villains.allVillains(function(villains_data){
      users_data.sort(function(a,b){return b["gameswon"]-a["gameswon"]});
      villains_data.sort(function(a,b){return b["gameswon"]-a["gameswon"]});

      response.status(200);
      response.setHeader('Content-Type', 'text/html')
      response.render('stats',{users:users_data, villains:villains_data});
    });
  });
});

module.exports = router;
