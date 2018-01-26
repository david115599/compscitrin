//Fishtank by David Bersadsky
//This program takes a set of parameiters and inputs and generates fish, they live amongst eacother,
//food and posin can be added to modify the size of fishes.
//the fish can also eat eachother in certian cases.
//the program uses objects to keep track of everything that is occuring and stores these objects in arrays.


/*var song;

function preload() {
  song = loadSound('Sound-of-water-running.mp3');
}
*/
// variables are defined
var f = [];
var p1 = [];
var p2 = [];
var buble = [];
var mx = 100;
var my = 100;
var t = 0;
var rand = 0;
//in setup the buttons for the various finction are added
function setup() {
  //song.loop();
  createCanvas(600, 600);
  fishButton = createButton('Spawn Gold');
  fishButton.position(152, 19);
  fishButton.mousePressed(addGold);
  foodButton = createButton('Spawn Food');
  foodButton.position(252, 19);
  foodButton.mousePressed(addFood);
  DeathButton = createButton('Spawn Death');
  DeathButton.position(352, 19);
  DeathButton.mousePressed(addDeath);
  fishButton1 = createButton('Spawn Piranha');
  fishButton1.position(452, 19);
  fishButton1.mousePressed(addPiranha);
  fishButton2 = createButton('Spawn Whale');
  fishButton2.position(552, 19);
  fishButton2.mousePressed(addWhale);
  fishButton3 = createButton('Spawn Spaz');
  fishButton3.position(652, 19);
  fishButton3.mousePressed(addSpaz);
  fishButton4 = createButton('Tap The Tank');
  fishButton4.position(752, 19);
  fishButton4.mousePressed(tap);
};


//in draw all of the collision checks occur for all of the various activities that occur
function draw() {
  background(113, 244, 255);
  for (var i = 0; i < f.length; i++) {
    for (var m = 1; m < f.length; m++) {
      //checks for fish collisions amongst themselves and if the coliding fish can eat eachother.
      if (f[i].Collisionfish(f[m]) & f[i].weight < f[m].weight & f[m].age <= f[m].maxage & f[m].weight <= f[m].maxweight & f[m].weight >= f[m].minweight) {
        f[m].weight = f[m].weight + f[i].weight / 4;
        f.splice(i--, 1);
        return;
      }
      //checks for fish collisions amongst themselves and uses to do breeding, not working properly, the screen fills up with infinte fish
      if (f[i].Collisionfish(f[m]) & f[i].weight == f[m].weight & f[m].age <= f[m].maxage & f[m].weight <= f[m].maxweight & f[m].weight >= f[m].minweight & Math.random() * 10 > 9) {
        //  addPiranha();
      }
      //checks for fish collisions amongst themselves and reverses direction if they are not eaten
      else if (f[i].Collisionfish(f[m]) & f[i].age <= f[i].maxage & f[i].weight <= f[i].maxweight & f[i].weight >= f[i].minweight) {
        f[i].vel.y *= -1;
        f[i].vel.x *= -1;
        f[i].tdi *= -1;;
        f[m].vel.y *= -1;
        f[m].vel.x *= -1;
        f[m].tdi *= -1;
      }
    }
    //var fish=f[i];
    //checks for fish collisions with food pelets
    if (f[i].age <= f[i].maxage & f[i].weight <= f[i].maxweight & f[i].weight >= f[i].minweight) {
      for (var p = 0; p < p1.length; p++) {
        if (f[i].Collision(p1[p])) {
          f[i].weight += p1[p].weight / 4;
          p1.splice(p--, 1);
        }
      }
      //checks for fish collisions with poisin pelets
      for (var q = 0; q < p2.length; q++) {
        if (f[i].Collision(p2[q])) {
          f[i].weight -= p2[q].weight / 4;
          p2.splice(q--, 1);
        }
      }
    }
    //updates all of the objects
    f[i].update();
  }
  for (var k = 0; k < p1.length; k++) {
    p1[k].update();
  }
  for (var z = 0; z < p2.length; z++) {
    p2[z].update();
  }
  for (var i = 0; i < buble.length; i++) {
    buble[i].update();
  }
  //randomly generates air bubbles in the tank
  console.log(rand);
  rand = Math.random() * 10;
  if (rand > 9.5) {
    console.log("hi");
    buble.push(createPeletc());
  }
}
//these are where all the spawing fuctions are defined and linked to their parameiters, the paramiter format is listed below
//no longer in use,no longer in use,type,start age,max age,start wieght,max weight,min weight,r,g,b,if the fish is a spaz 0 == no 255 == yes,speed
function addGold() {
  f.push(createFish(width / 2, height / 2, "goldfish", 0, 1000, 10, 30, 5, random(105) + 150, random(105) + 150, 0, 0, 3));
}

function addPiranha() {
  f.push(createFish(width / 2, height / 2, "Piranha", 0, 10000, 25, 50, 10, random(55) + 180, random(55) + 200, random(55) + 180, 0, 2));
}

function addWhale() {
  f.push(createFish(width / 2, height / 2, "Whale", 0, 30000, 50, 100, 25, random(25) + 180, random(25) + 200, random(25) + 200, 0, 1));
}

function addSpaz() {
  f.push(createFish(width / 2, height / 2, "Spaz", 0, 1000, 10, 30, 5, 113, 244, 255, 255, 3));
}
//this function flips the tank
function tap() {
  for (var i = 0; i < f.length; i++) {
    f[i].vel.y *= -1;
    f[i].vel.x *= -1;
    f[i].tdi *= -1;;
    if (rand > 9.5) {
      f[i].age += 500;
    }
  }
}
//adds food pelets
function addFood() {
  for (var i = 0; i < 8; i++)
    p1.push(createPelet());
}
//adds poison pellets
function addDeath() {
  for (var i = 0; i < 8; i++)
    p2.push(createPeletD());
}
