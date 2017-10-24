var r = (10);
var x = (0);
var y = (0);
var a = (0);
var b = (0);
var slider;
var val;
var boxSize = 75;
var overBox = false;
var locked = false;
var overBox2 = false;
var locked2 = false;
var bx;
var by;
var theta = 0;
var x1, y1, centerX, centerY;
var speed;
function setup(){
createCanvas (500,500);
 slider = createSlider(0, width, 100);
 slider.position(10, 10);
 slider.style('width', '80px');
 centerX=width/2;
 centerY=height/2;
 smooth();
 font=loadFont("ArialMT-48.vlw");
 textAlign(LEFT);
 textFont(font, 12);
 speed= .02;
}
function draw(){
background(150,150,150);
drawGrid(25);
translate(width/2, height/2);
fill(0,255,255)
ellipse (x-a, y-b, r, r);
val = slider.value();
r = val;
translate(-width/2, -height/2);
x1=cos(theta)*r/2 + centerX;
y1=sin(theta)*r/2 + centerY
ellipse(x1, y1, 10, 10);
theta=theta+speed;

}



function drawGrid(size){
for (var i = 0; i < width; i += 10){
  line(0,i,width,i);
  line(i,0,i,height)
  }
  strokeWeight(3);
  line(width/2,0,width/2,height);
  line(0,height/2,width,height/2);
  strokeWeight(1);
}
