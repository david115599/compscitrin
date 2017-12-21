var f1 = [];
var p1 = [];
var mx = 100;
var my = 100;
var x = 1;
var q = 1;
function setup() {
  createCanvas(600, 600);

}

function draw() {
  background(113, 244, 255);
  for (var i = 0; i < f1.length; i++){
    f1[i].update();
    }
  for (var i = 0; i < p1.length; i++){
    p1[i].update();
    }

          if (x > 0 & x < 25) {
            x = x+q;
          }
          else{
            q = -q
            x = x+q;
          }
}
function mousePressed() {
mx = mouseX;
my = mouseY;
f1.push(createFish(mx,my,x));
}
function keyPressed() {
p1.push(createPelet());
}
