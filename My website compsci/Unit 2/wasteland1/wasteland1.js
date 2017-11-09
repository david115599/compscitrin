var r = (10);
var a = (0);
var b = (0);
var i = 0;
var slider;
var bx;
var by;
var a1=0; //angle
var x1=0; //triangle coordinate x
var y1=0; //triangle coordinate y
var mx=0;
var my=0;

function setup(){
createCanvas (1000,500);

 slider = createSlider(0, 500, 500);
 slider.position(10, 10);
 slider.style('width', '80px');
}

function draw(){
  background(150,150,150);
translate(500,0);
  fill(0,255,0);
  textSize(32);
  text(nf(("(x-"))+((mx-height/2)/(25/2))+(")^2+(y-")+((my-width/2)/(25/2))+(")^2=")+(((r/2)/(25/2))*((r/2)/(25/2))),20,30);
  text((nf(x1,3,0)+("=")+((r/2))+("cos(")+nf(a1/2,1,2)+(")")), 20, 60);
  text((nf(y1,3,0)+("=")+((r/2))+("sin(")+nf(a1/2,1,2)+(")")), 20, 90);
  text(("Area = ")+nf(PI*(((r/2)/(25/2))*((r/2)/(25/2))),4,1), 20, 120);
  text(("Circumference = ")+nf(PI*(2*(r/2)),4,1), 20, 150);
translate(-500,0);
  drawGrid(25);
  fill(0,255,0);
  ellipse (mx, my, r, r);
  fill(255,0,0);
  polygon(mx,my,r/2,5, i += 0.01);
  r = slider.value();
  if (a1<=360) {

a1+=.01;

}
else {
a1=0;
}

drawRight();
}

function drawGrid(size){
for (var i = 0; i < 500; i += 25/2){
  line(0,i,500,i);
  line(i,0,i,500);
  }

  for (var x = 0; x < 500; x += 125/2){
  strokeWeight(3);
  line(x, 240, x, 260);
  line(240, x, 260, x);
  }

  strokeWeight(3);
  line(250,0,250,500);
  line(0,250,500,250);
  strokeWeight(1);

}

function drawRight() {



translate(mx,my);
x1 = r*cos(a1)/2 ;
y1 = r*sin(a1)/2 ;

fill(255,0,0);
polygon(0,0,r/2,5);
fill(0,0,255);
triangle(0, 0, x1, y1,x1,0);
triangle(0,0,-x1,-y1,-x1,0);

}
function mouseReleased() {
  if(mouseReleased) {
    if (mouseX < 500-r/2 & mouseY < 500-r/2 & mouseX > r/2 & mouseY > r/2) {
    mx=mouseX;
    my=mouseY;
    mx-=mx%(125/2);
    my-=my%(125/2);
  }

}
}

function polygon(x, y, radius, npoints, spin) {
  var angle = TWO_PI / npoints;
  beginShape();
  for (var a = 0 + spin; a < TWO_PI + spin; a += angle) {
    var sx = x + cos(a) * radius;
    var sy = y + sin(a) * radius;
    vertex(sx, sy);
  }
  endShape(CLOSE);
}
