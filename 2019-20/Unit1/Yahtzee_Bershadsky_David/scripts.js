/*
Yahtzee
David Bershadsky
11/5/19
This version of yahtzee auto fills the score values for the player making the game easier,
also the game only autosaves at the end of a game so to resume a game you must press save game.
*/
console.log("Scripts.js loaded");
var users  =
{
  name: "testuser",
  current_round:1,
  current_dice:[1,2,3,3,4],
  current_gamevals: [1,0,1,1,11,1,1,1,11,1,1,1,1,1],
  current_locks: [true,false,true,true,true,true,true,true,true,true,true,true,true,true],
  past_games_scores:[]
}
//the lines above define a test user which can be loded to verify game loading code
var user;
window.localStorage.setItem(users.name, JSON.stringify(users));
var totalup = 0;//total score of upper section
var uptotal = 0;//total score of upper section after bonus
var totallow = 0;//total score of lowwer section after bonus
var roundcount=1;
var roll_button = document.getElementById("roll_button");
var rolls = 3;
var remaining_rolls =  document.getElementById("remaining_rolls");
var die_1 = document.getElementById("die_1");
var die_2 = document.getElementById("die_2");
var die_3 = document.getElementById("die_3");
var die_4 = document.getElementById("die_4");
var die_5 = document.getElementById("die_5");
var one;//die value for die x
var two;//die value for die x
var three;//die value for die x
var four;//die value for die x
var five;//die value for die x
var onelock = false;//die lock for die x
var twolock = false;//die lock for die x
var threelock = false;//die lock for die x
var fourlock = false;//die lock for die x
var fivelock = false;//die lock for die x
var finalvalues = [];//array of final values
var hasthreeofakind = false;
var haspair = false;
var three_of_a_kindlock = false;
var four_of_a_kindlock = false;
var full_houselock = false;
var small_straightlock = false;
var large_straightlock = false;
var yahtzeelock = false;
var chancelock = false;
var divs = [aces, twos, threes, fours, fives, sixes, three_of_a_kind, four_of_a_kind, full_house, small_straight, large_straight, yahtzee, chance];// an array of all my score divs
var divss = ["aces", "twos", "threes", "fours", "fives", "sixes",  "three_of_a_kind", "four_of_a_kind", "full_house", "small_straight", "large_straight", "yahtzee", "chance"];// an array of all my score divs names as strings
var locks = [false, false, false, false, false, false, false, false, false, false, false, false, false];// an array of lock values for score section
var finval = [0,0,0,0,0,0,0,0,0,0,0,0,0,0];//array for score values for the end of each round
var finvals = [0,0,0,0,0,0,0,0,0,0,0,0,0,0];//array for final score values to be saved and to repopulate score fields

var value;//variable to store username
//code that handles logins
var login = document.getElementById("login");
login.addEventListener('keyup', function(){
  if (event.keyCode === 13) {
    var safe = true;
    var invalidinputs = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?";//checks for special characters and throws error if present
    for (var i = 0; i < invalidinputs.length; i++)
    {
      if (this.value.includes(invalidinputs.charAt(i)))
      {
        safe = false;
        console.log("invalid");
        alert ("Contains invalid inputs");
      }
    }
    if (safe === true) {
      value = this.value;
      console.log(value);
      currentuser.innerHTML = value;
    }

    user = JSON.parse(window.localStorage.getItem(value))//fetches user data from localStorage if present
    if (user === null) {//if not present define new empty user and save them to local storage
      user = {
        name: value,
        current_round:1,
        current_dice:[null,null,null,null,null],
        current_gamevals: [0,0,0,0,0,0,0,0,0,0,0,0,0,0],
        current_locks:[false,false,false,false,false,false,false,false,false,false,false,false,false,false],
        past_games_scores:[]
      }
      window.localStorage.setItem(user.name, JSON.stringify(user));
    }
    if (confirm("To resume a game press ok. to start a new game press Cancel")) {//asks if they want to resume or start new
      finalvalues = user.current_dice;//loads all previous game values onto the screen
      one = user.current_dice[0];
      two = user.current_dice[1];
      three = user.current_dice[2];
      four = user.current_dice[3];
      five = user.current_dice[4];
      finvals = user.current_gamevals;
      locks = user.current_locks;
      roundcount = user.current_round;
      current_rounds.innerHTML = roundcount;
      die_1.innerHTML =" <button type='button'><img src=assets\\"+one.toString()+".svg alt='' height=100 width=60></img></button>"
      die_2.innerHTML =" <button type='button'><img src=assets\\"+two.toString()+".svg alt='' height=100 width=60></img></button>"
      die_3.innerHTML =" <button type='button'><img src=assets\\"+three.toString()+".svg alt='' height=100 width=60></img></button>"
      die_4.innerHTML =" <button type='button'><img src=assets\\"+four.toString()+".svg alt='' height=100 width=60></img></button>"
      die_5.innerHTML =" <button type='button'><img src=assets\\"+five.toString()+".svg alt='' height=100 width=60></img></button>"
      if (one == 0) {
        die_1.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_2.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_3.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_4.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_5.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
      }
      scoreoptions();//runs score validation code to populate score values
    }
    else {//loads new game if user selects it
      user.current_round = 1;
      user.current_dice = [null,null,null,null,null];
      user.current_gamevals = [0,0,0,0,0,0,0,0,0,0,0,0,0,0];
      user.current_locks = [false,false,false,false,false,false,false,false,false,false,false,false,false,false];
    }
  }
});
save.addEventListener('click', function() {//saves current game to user in localStorage
  if (roundcount === 15) {

    user.current_round = 1;
    user.current_dice = [null,null,null,null,null];
    user.current_gamevals = [0,0,0,0,0,0,0,0,0,0,0,0,0,0];
    user.current_locks = [false,false,false,false,false,false,false,false,false,false,false,false,false,false];
    user.past_games_scores.push(uptotal+totallow);
  }
  else{
    user.current_round = roundcount;
    user.current_dice = [one,two,three,four,five];
    user.current_gamevals = finvals;
    user.current_locks = locks;
  }
  window.localStorage.setItem(user.name, JSON.stringify(user));
});


function scoreoptions() {//uses die values to populate score fields
  hasthreeofakind = false;
  haspair = false;
  var count = 0;
  //  finalvaluesclean = finalvalues.reduce(function (s, v) { return s + (v || 0); });
  finalvaluesclean = one+two+three+four+five;//finds sum of all die values
  for (var i = 0; i < divs.length; i++) {//populates all nonlocked fields with 0 to remove old values
    if (locks[i] === false) {
      (divs[i]).innerHTML =(0).toString();
    }
  }
  if (chancelock == false){//populates chance
    (chance).innerHTML ="<button type='button'>"+finalvaluesclean+"</button>";
    finval[12] = finalvaluesclean;
  }
  //populates small_straight
  if (small_straightlock==false && ((finalvalues.includes(1)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4)) | (finalvalues.includes(5)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4)) | ((finalvalues.includes(5)&&finalvalues.includes(6)&&finalvalues.includes(3)&&finalvalues.includes(4))))){
    (small_straight).innerHTML ="<button type='button'>"+(30)+"</button>";
    finval[9] = 30;
  }
  else if (small_straightlock==false) {
    (small_straight).innerHTML ="<button type='button'>"+(0)+"</button>";
  }
  //populates large_straight
  if (large_straightlock==false && ((finalvalues.includes(1)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4) &&finalvalues.includes(5)) |(finalvalues.includes(5)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4)&&finalvalues.includes(6)))){
    (large_straight).innerHTML ="<button type='button'>"+(40)+"</button>";
    finval[10] = 40;
  }
  else if (large_straightlock==false) {
    (large_straight).innerHTML ="<button type='button'>"+(0)+"</button>";
  }
  //populates yahtzee
  if (yahtzeelock==false && finalvalues[0]==finalvalues[1] && finalvalues[1]==finalvalues[2] && finalvalues[2]==finalvalues[3] && finalvalues[3]==finalvalues[4] && onelock == true && twolock == true && threelock == true && fourlock == true && fivelock == true){
    (yahtzee).innerHTML ="<button type='button'>"+(50)+"</button>";
    finval[11] = 50;
  }
  else if (yahtzeelock==false) {
    (yahtzee).innerHTML ="<button type='button'>"+(0)+"</button>";
  }

  (three_of_a_kind).innerHTML ="<button type='button'>"+(0)+"</button>";
  (four_of_a_kind).innerHTML ="<button type='button'>"+(0)+"</button>";

  //cycles through all score values to find repeating scores for three_of_a_kind or four_of_a_kind
  var kcount=0;
  for (var val = 1; val <= 6; val++) {
    count = 0;
    for (var i = 0; i < finalvalues.length; i++) {
      if (finalvalues[i]==val) {
        count++;
      }
      if (count>=3) {
        kcount=count
      }
    }
    if (locks[val-1] === false) {
      finval[val-1]=count*val;
      ((divs[val-1])).innerHTML =" <button type='button'>"+(count*val)+"</button>";
    }
  }
  if (three_of_a_kindlock === false && (kcount == 3 || kcount == 4)) {
    three_of_a_kind.innerHTML =" <button type='button'>"+(finalvaluesclean)+"</button>";
    finval[6] = finalvaluesclean;

  }
  if (four_of_a_kindlock === false &&kcount == 4) {
    four_of_a_kind.innerHTML =" <button type='button'>"+(finalvaluesclean)+"</button>";
    finval[7] = finalvaluesclean;
  }

  //cycles through all score values to find repeating scores for full_house
  var count1=0;
  var count2=0;
  for (var val = 1; val <= 6; val++) {
    count = 0;
    for (var i = 0; i < finalvalues.length; i++) {
      if (finalvalues[i]==val && finalvalues[i]!= null && finalvalues[i]!= 0 && finalvalues[i]!= undefined) {
        if (finalvalues[i]==val && finalvalues[i]!= null && finalvalues[i]!= 0 && finalvalues[i]!= undefined) {
          count++;
        }
        if (count==2) {
          if (count1 ==0) {
            count1=count
          }
        }
        if (count==3) {
          if (count1 ==0) {
            count1=0
          }
          count2=count
        }
      }
    }
  }
  //populates full house
  if (full_houselock === false) {
    (full_house).innerHTML ="<button type='button'>"+(0)+"</button>";
  }
  if (count1 + count2 ==5 && count2 >=2 && count1 >=2 && onelock === true&& twolock === true&& threelock === true && fourlock === true && fivelock === true/* 5==count1[6]+count1[0]+count1[1]+count1[2]+count1[3]+count1[4]+count1[5]*/ && full_houselock === false){
    console.log('here');
    finval[8] = 25;
    (full_house).innerHTML ="<button type='button'>"+(25)+"</button>";
  }

  //populates all fields from fivals to ensure that all values are correct
  for (var i = 0; i < divs.length; i++) {
    if (locks[i] === true) {
      (divs[i]).innerHTML =(finvals[i]).toString();
      console.log("alive");
      console.log(divs[i],locks[i],finval[i]);
    }
  }

  //populates total fields
  totalup = finvals[0]+finvals[1]+finvals[2]+finvals[3]+finvals[4]+finvals[5];
  uptotal = totalup;
  total_score.innerHTML =totalup;
  if (totalup>=63) {
    bonus.innerHTML =(35).toString();
    uptotal+=35
  }
  total.innerHTML =(uptotal);
  total_of_upper_section.innerHTML =(uptotal);
  totallow = finvals[6]+finvals[7]+finvals[8]+finvals[9]+finvals[10]+finvals[11]+finvals[12]+finvals[13];
  total_of_lower_section.innerHTML =(totallow);
  grand_total.innerHTML =(totallow+uptotal);
}



//adds event listeners for score values to detect clicks and resets for next round when clicked
for (var i = 0; i < divs.length; i++) {
  if (locks[i] === false) {
    (divs[i]).addEventListener('click', function() {
      if (rolls != 3 && roundcount<13) {
        roundcount++
        current_rounds.innerHTML = roundcount;
        locks[divss.indexOf(this.id.toString())] = true;
        finvals[divss.indexOf(this.id.toString())]=finval[divss.indexOf(this.id.toString())];
        rolls=3;
        console.log(finval);
        console.log(divss.indexOf(this.id.toString()));
        console.log(this.id.toString());
        console.log(finvals);
        console.log(locks);
        onelock = false
        twolock = false
        threelock = false
        fourlock = false
        fivelock = false
        one = 0;
        two = 0;
        three = 0;
        four = 0;
        five = 0;
        die_1.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_2.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_3.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_4.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        die_5.innerHTML =" <button type='button'><img src=assets\\question_mark.png alt='' height=100 width=60></img></button>"
        remaining_rolls.innerHTML = rolls.toString();
        for (var i = 0; i < finalvalues.length; i++) {
          finalvalues[i]=undefined;
        }
        for (var i = 0; i < finval.length; i++) {
          finval[i] = 0;
        }
      }
      else if(roundcount == 13) {
        //autosaves if game is over and reloads if user requests another game
        console.log("gameover");
        user.current_round = 1;
        user.current_dice = [null,null,null,null,null];
        user.current_gamevals = [0,0,0,0,0,0,0,0,0,0,0,0,0,0];
        user.current_locks = [false,false,false,false,false,false,false,false,false,false,false,false,false,false];
        user.past_games_scores.push(uptotal+totallow);
        window.localStorage.setItem(user.name, JSON.stringify(user));
        if (confirm("To play again press ok")){
          location.reload();
        }
      }
      scoreoptions();//updates score values
    });
  }
}
function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

//die reserving code
die_1.addEventListener('click', function() {
  if (onelock == false) {
    die_1.innerHTML ="<img src=assets\\"+one.toString()+".svg alt='' height=100 width=60></img>"
    onelock = true
    finalvalues[0] = one;
    scoreoptions();
    return;
  }
  if (onelock == true) {
    die_1.innerHTML ="<button type='button'><img src=assets\\"+one.toString()+".svg alt='' height=100 width=60></img></button>"
    onelock = false
    finalvalues[0] = undefined;
    scoreoptions();
    return;
  }
});
die_2.addEventListener('click', function() {
  if (twolock == false) {
    die_2.innerHTML ="<img src=assets\\"+two.toString()+".svg alt='' height=100 width=60></img>"
    twolock = true
    finalvalues[1] = two;
    scoreoptions();
    return;
  }
  if (twolock == true) {
    die_2.innerHTML ="<button type='button'><img src=assets\\"+two.toString()+".svg alt='' height=100 width=60></img></button>"
    twolock = false
    finalvalues[1] = undefined;
    scoreoptions();
    return;
  }
});
die_3.addEventListener('click', function() {
  if (threelock == false) {
    die_3.innerHTML =" <img src=assets\\"+three.toString()+".svg alt='' height=100 width=60></img>"
    threelock = true
    finalvalues[2] = three;
    scoreoptions();
    return;
  }
  if (threelock == true) {
    die_3.innerHTML =" <button type='button'><img src=assets\\"+three.toString()+".svg alt='' height=100 width=60></img></button>"
    threelock = false
    finalvalues[2] = undefined;
    scoreoptions();
    return;
  }
});
die_4.addEventListener('click', function() {
  if (fourlock == false) {
    die_4.innerHTML =" <img src=assets\\"+four.toString()+".svg alt='' height=100 width=60></img>"
    fourlock = true
    finalvalues[3] = four;
    scoreoptions();
    return;
  }
  if (fourlock == true) {
    die_4.innerHTML =" <button type='button'><img src=assets\\"+four.toString()+".svg alt='' height=100 width=60></img></button>"
    fourlock = false
    finalvalues[3] = undefined;
    scoreoptions();
    return;
  }
});
die_5.addEventListener('click', function() {
  if (fivelock == false) {
    die_5.innerHTML =" <img src=assets\\"+five.toString()+".svg alt='' height=100 width=60></img>"
    fivelock = true
    finalvalues[4] = five;
    scoreoptions();
    return;
  }
  if (fivelock == true) {
    die_5.innerHTML =" <button type='button'><img src=assets\\"+five.toString()+".svg alt='' height=100 width=60></img></button>"
    fivelock = false
    finalvalues[4] = undefined;
    scoreoptions();
    return;
  }
});

//sets die values and updates html when rolled
roll_button.addEventListener('click', function(){
  for (var i = 0; i < finalvalues.length; i++) {
    console.log(finalvalues[i]);
  }
  if (rolls>0/*true*/ ) {
    if (onelock == false) {
      one = getRandomInt(6)+1;
      die_1.innerHTML =" <button type='button'><img src=assets\\"+one.toString()+".svg alt='' height=100 width=60></img></button>"
    }
    if (twolock == false) {
      two = getRandomInt(6)+1;
      die_2.innerHTML =" <button type='button'><img src=assets\\"+two.toString()+".svg alt='' height=100 width=60></img></button>"
    }
    if (threelock == false) {
      three = getRandomInt(6)+1;
      die_3.innerHTML =" <button type='button'><img src=assets\\"+three.toString()+".svg alt='' height=100 width=60></img></button>"
    }
    if (fourlock == false) {
      four = getRandomInt(6)+1;
      die_4.innerHTML =" <button type='button'><img src=assets\\"+four.toString()+".svg alt='' height=100 width=60></img></button>"
    }
    if (fivelock == false) {
      five = getRandomInt(6)+1;
      die_5.innerHTML =" <button type='button'><img src=assets\\"+five.toString()+".svg alt='' height=100 width=60></img></button>"
    }
    rolls = rolls-1;
    console.log("roll button clicked");
    remaining_rolls.innerHTML = rolls.toString();
    scoreoptions();
  }});
