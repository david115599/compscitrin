var f = [];
var p1 = [];
var p2 = [];
var mx = 100;
var my = 100;
var t = 0;

function setup() {
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
  fishButton3.position(552, 19);
  fishButton3.mousePressed(addSpaz);
  };



function draw() {
  background(113, 244, 255);
  for (var i = 0; i < f.length; i++){
    //var fish=f[i];
if (f[i].age <= f[i].maxage & f[i].weight <= f[i].maxweight & f[i].weight >= f[i].minweight) {
    for (var p = 0; p < p1.length; p++){
      if (f[i].Collision(p1[p])) {
       f[i].weight += p1[p].weight/4;
       p1.splice(p--, 1);
       }}
       for (var q = 0; q < p2.length; q++){
         if (f[i].Collision(p2[q])) {
          f[i].weight -= p2[q].weight/4;
          p2.splice(q--, 1);

          }}

}
    f[i].update();
}
  for (var k = 0; k < p1.length; k++){
    p1[k].update();
    }
  for (var z = 0; z < p2.length; z++){
      p2[z].update();
    }
}

//mx,my,t,a,ma,w,mxw,mw,r,g,b
function addGold() {
  f.push(createFish(width/2,height/2,"goldfish",0,1000,10,30,5,255,255,0));
}
function addPiranha() {
  f.push(createFish(width/2,height/2,"Piranha",0,10000,25,50,10,100,255,150));
}
function addWhale() {
  f.push(createFish(width/2,height/2,"Whale",0,30000,50,100,25,200,255,255));
}
function addSpaz() {
  f.push(createFish(width/2,height/2,"Spaz",0,1000,10,30,5,255,255,0));
}

function addFood(){
for (var i = 0; i < 8; i++)
p1.push(createPelet());
}
function addDeath(){
for (var i = 0; i < 8; i++)
p2.push(createPeletD());
}
