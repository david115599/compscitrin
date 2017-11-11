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
var xint = 0;
var movement = .01;
var triangleMove = 1;
var pause;
var numSides = 3;

function setup(){
createCanvas (1000,500);

 slider = createSlider(0, 500, 200);
 slider.position(550, 15);
 slider.style('width', '80px');
}

function draw(){
  background(150,150,150);
translate(500,25);
  fill(0,255,0);
  textSize(32);
  text(nf(("(x-"))+((mx-height/2)/(25/2))+(")^2+(y-")+((my-width/2)/(25/2))+(")^2=")+(((r/2)/(25/2))*((r/2)/(25/2))),20,30);
  text(("x"+("=")+((r/2))+("cos(")+nf(a1/2,1,2)+(")")), 20, 60);
  text(("y"+("=")+((r/2))+("sin(")+nf(a1/2,1,2)+(")")), 20, 90);
  text(("Area = ")+nf(PI*(((r/2)/(25/2))*((r/2)/(25/2))),4,1), 20, 120);
  text(("Circumference = ")+nf(PI*(2*((r/2)/(25/2))),4,1), 20, 150);
  text(("Angle = ")+(a1*(180/PI)),20,180);
  text("Press Spacebar to pause.", 20, 210);
translate(-500,-25);
  drawGrid(25);
  fill(255,0,0);
  polygon(mx,my,r/2,numSides, i += movement);
  fill(0,255,0);
  ellipse (mx, my, r, r);
  fill(255,0,0);
  polygon(mx,my,r/2,numSides, i += movement);
  r = slider.value();
  if (a1<=3.12 && pause == false) {

a1+=.01;

}
else {
a1=0;
}

drawRight();

}

function drawGrid(size){
for (var i = 0; i < 501; i += 25/2){
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
x1 = triangleMove*(r*cos(a1)/2) ;
y1 = triangleMove*(r*sin(a1)/2) ;

fill(255,0,0);
polygon(0,0,r/2,numSides);
fill(0,0,255);
triangle(0, 0, x1, y1,x1,0);
triangle(0,0,-x1,-y1,-x1,0);

}
function mouseReleased() {
  if(mouseReleased) {
    if (mouseX < 500-r/2 & mouseY < 500-r/2 & mouseX > r/2 & mouseY > r/2) {
    mx=mouseX;
    my=mouseY;
    mx-=mx%(500/40);
    my-=my%(500/40);
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

function keyTyped(){
  if (key === ' ')
    pause = !pause ;
  if (pause == true) {
    movement = 0 ;
    x1 = 0 ;
    y1 = 0 ;
    triangleMove = 0 ;
        a1 += 0;
    }
  else {
    movement = 0.01 ;
    triangleMove = 1 ;
    x1 = triangleMove*(r*cos(a1)/2) ;
    y1 = triangleMove*(r*sin(a1)/2) ;
    a1 = 0.01 ;
  }
  if (key === '2')
  	numSides = 2;
  else if (key === '3')
  	numSides = 3;
  else if (key === '4')
  	numSides = 4;
  else if (key === '5')
  	numSides = 5;
  else if (key === '6')
  	numSides = 6;
  else if (key === '7')
  	numSides = 7;
  else if (key === '8')
  	numSides = 8;
  else if (key === '9')
  	numSides = 9;
}
