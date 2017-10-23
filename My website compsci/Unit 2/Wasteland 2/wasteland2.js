var theta = 0;
var radius = 80;
var x, y, centerX, centerY;
var speed;

function setup() {
  createCanvas(400, 400);
  centerX=width/2;
  centerY=height/2;
  smooth();
  font=loadFont("ArialMT-48.vlw");
  textAlign(LEFT);
  textFont(font, 12);
  speed= .001;
}

function draw() {
  background(100);
  drawGrid();

  noFill();
  stroke(255);
  ellipse(width/2, height/2, 2*radius, 2*radius);

  x=cos(theta)*radius + centerX;
  y=sin(theta)*radius+ centerY;
  noStroke();
  fill(255);
  ellipse(x, y, 10, 10);
  theta=theta+speed;
  text("Angle in Radians= "+theta, 20, 30);
  text("Angle in Degrees="+degrees (theta), 20, 50);
  text("radius="+radius, 20, 70);
}

function drawGrid() {
  line(200,200,250,250);
}

function keyPressed() {
  if (key=='s');
  radius=radius +12;
  if(key=='w');
  radius=radius + 40;
  if(key== 'x');
  radius=radius - 300;

}
