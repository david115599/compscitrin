//FishTales Demo I
//introduces objects & vectors

var f1;

function setup() {
  createCanvas(600, 600);
  f1 = createFish();
}

function draw() {
  background(113, 244, 255);
  f1.update();
}
