var f = [];
var p1 = [];
var p2 = [];
var mx = 100;
var my = 100;

function setup() {
  createCanvas(600, 600);
  fishButton = createButton('Spawn Fish');
  fishButton.position(152, 19);
  fishButton.mousePressed(addFish);
  foodButton = createButton('Spawn Food');
  foodButton.position(252, 19);
  foodButton.mousePressed(addFood);
  DeathButton = createButton('Spawn Death');
  DeathButton.position(352, 19);
  DeathButton.mousePressed(addDeath);
  };


function draw() {
  background(113, 244, 255);
  for (var i = 0; i < f.length; i++){
    //var fish=f[i];
if (f[i].age <= 1000 & f[i].weight <= 80) {
    for (var p = 0; p < p1.length; p++){
      if (f[i].Collision(p1[p])) {
       f[i].weight += p1[p].weight/4;
       p1.splice(p--, 1);
       }}
       for (var q = 0; q < p2.length; q++){
         if (f[i].Collision(p2[q])) {
          f[i].weight -= p2[q].weight/4;
          p2.splice(q--, 1);
        p2[q].update();
          }}

}
    f[i].update();
}
  for (var k = 0; k < p1.length; k++){
    p1[k].update();
    }
}
function addFish() {
  f.push(createFish(width/2,height/2));
}

function addFood(){
for (var i = 0; i < 8; i++)
p1.push(createPelet());
}
function addDeath(){
for (var i = 0; i < 8; i++)
p2.push(createPeletD());
}
