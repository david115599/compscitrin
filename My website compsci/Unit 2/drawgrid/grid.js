var r = (10);
var x = (200);
var y = (200);
var a = (0);
var b = (0);
var slider;
var boxSize = 75;
var overBox = false;
var locked = false;
var overBox2 = false;
var locked2 = false;
var bx;
var by;
var radius;
function setup(){
createCanvas (500,500);
 slider = createSlider(0, 255, 100);
 slider.position(10, 10);
 slider.style('width', '80px');
 background(150,150,150);
 drawGrid(25);
}

function draw(){

}

function mouseClicked() {
  radius = slider.value();
  ellipse (mouseX, mouseY, radius, radius);
  return false;
}  

function drawGrid(size){
for (var i = 0; i < width; i += 10){
  line(0,i,width,i);
  line(i,0,i,height);
  }

  for (var x = 0; x < width; x += 50){
  strokeWeight(3);
  line(x, 240, x, 260);
  line(240, x, 260, x);
  }

  strokeWeight(3);
  line(width/2,0,width/2,height);
  line(0,height/2,width,height/2);
  strokeWeight(1);

}
