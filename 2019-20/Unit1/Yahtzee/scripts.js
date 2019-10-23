console.log("Scripts.js loaded");
/*if ((null == false)==false) {
console.log("its false");
}
if ((null == false)==true) {
console.log("its true");
}*/
var user  = {
  total_rolls: 0,
  tunr_rolls: 2,
  dice:[1,2,3,3,4],
  name: "david"
}
window.localStorage.setItem(user.name, JSON.stringify(user));


var roll_button = document.getElementById("roll_button");
var rolls = 3;
var remaining_rolls =  document.getElementById("remaining_rolls");
var die_1 = document.getElementById("die_1");
var die_2 = document.getElementById("die_2");
var die_3 = document.getElementById("die_3");
var die_4 = document.getElementById("die_4");
var die_5 = document.getElementById("die_5");
var one;
var two;
var three;
var four;
var five;
var onelock = false;
var twolock = false;
var threelock = false;
var fourlock = false;
var fivelock = false;
var finalvalues = [];
/*var aces = document.getElementById("aces");
var twos = document.getElementById("twos");
var threes = document.getElementById("threes");
var fours = document.getElementById("fours");*/
var hasthreeofakind = false;
var haspair = false;

var aceslock = false;
var twoslock = false;
var threeslock = false;
var fourslock = false;
var fiveslock = false;
var sixeslock = false;
var three_of_a_kindlock = false;
var four_of_a_kindlock = false;
var full_houselock = false;
var small_straightlock = false;
var large_straightlock = false;
var yahtzeelock = false;
var chancelock = false;
var divs = [aces, twos, threes, fours, fives, sixes];
var divss = ["aces", "twos", "threes", "fours", "fives", "sixes"];
var locks = [aceslock, twoslock, threeslock, fourslock, fiveslock, sixeslock];
var finval = [0,0,0,0,0,0];
var finvals = [0,0,0,0,0,0];
function scoreoptions() {
  hasthreeofakind = false;
  haspair = false;
  var count = 0;
  finalvaluesclean = finalvalues.reduce(function (s, v) { return s + (v || 0); });

  for (var i = 0; i < divs.length; i++) {
    if (locks[i] === false) {
      (divs[i]).innerHTML =(0).toString();
    }
  }
  for (var i = 0; i < divs.length; i++) {
    if (locks[i] === false) {
      (divs[i]).addEventListener('click', function() {
        locks[i] = true;
        finvals[i]=finval[i];
        console.log(finvals);
      });
    }
  }
  if (chancelock == false){
    (chance).innerHTML ="<button type='button'>"+finalvalues.reduce(function (s, v) { return s + (v || 0); }, 0)+"</button>";
  }
  if (small_straightlock==false && ((finalvalues.includes(1)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4)) | (finalvalues.includes(5)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4)) | ((finalvalues.includes(5)&&finalvalues.includes(6)&&finalvalues.includes(3)&&finalvalues.includes(4))))){
    (small_straight).innerHTML ="<button type='button'>"+(30)+"</button>";
  }
  if (large_straightlock==false && ((finalvalues.includes(1)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4) &&finalvalues.includes(5)) |(finalvalues.includes(5)&&finalvalues.includes(2)&&finalvalues.includes(3)&&finalvalues.includes(4)&&finalvalues.includes(6)))){
    (large_straight).innerHTML ="<button type='button'>"+(40)+"</button>";
  }
  if (yahtzeelock==false && finalvalues[0]==finalvalues[1] && finalvalues[1]==finalvalues[2] && finalvalues[2]==finalvalues[3] && finalvalues[3]==finalvalues[4] && onelock == true && twolock == true && threelock == true && fourlock == true && fivelock == true){
    (yahtzee).innerHTML ="<button type='button'>"+(50)+"</button>";
  }
  for (var val = 1; val <= 6; val++) {
    count = 0;
      for (var i = 0; i < finalvalues.length; i++) {
        if (finalvalues[i]==val) {
          count++;
        }
        if (locks[val-1] === false && i == finalvalues.length-1) {
          finval[val]=count*val;
          ((divs[val-1])).innerHTML =" <button type='button'>"+(finval[val])+"</button>";

        }
        if (three_of_a_kindlock === false && count == 3) {
          (three_of_a_kind).innerHTML =" <button type='button'>"+(count*val)+"</button>";
        }
        if (four_of_a_kindlock === false &&count == 4) {
          (four_of_a_kind).innerHTML =" <button type='button'>"+(count*val)+"</button>";
        }
      }

  }

  var count1 = 0;
  var count2 = 0;

  for (var i = 0; i <= finalvalues.length; i++) {
    if (finalvalues[0] == finalvalues[i] && finalvalues[i] != 0 && finalvalues[i] != undefined) {
      count1++
    }
    else if (i != 0 && finalvalues[i] != 0 && finalvalues[i] != undefined){
      for (var q = i; q<=finalvalues.length; q++){
        if (finalvalues[i]==finalvalues[q]) {
          count2++;
        }
      }
    }
    if (count1 + count2 ==5 && count2 >=2 && count1 >=2 ){
      (full_house).innerHTML ="<button type='button'>"+(25)+"</button>";
    }
  }
  for (var i = 0; i < divs.length; i++) {
    if (locks[i] === true) {
      (divs[i]).innerHTML =(finvals[i]).toString();
    console.log("alive");
      console.log(divs[i],locks[i],finval[i]);
    }

  }
}

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

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
    rolls = rolls-1
    console.log("roll button clicked")
    remaining_rolls.innerHTML = rolls.toString()
    scoreoptions()
  }});
