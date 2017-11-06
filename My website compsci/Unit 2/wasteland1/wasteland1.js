var r = (10);
var x = (200);
var y = (200);
var a = (0);
var b = (0);
var slider;
var val = 0;
var bx;
var by;
var a1=0; //angle
var x1=0;//triangle coordinate x
var y1=0;// triangle coordinate y
var mx=0;
var my=0;
function setup(){
createCanvas (500,500);

 slider = createSlider(0, 500, 500);
 slider.position(10, 10);
 slider.style('width', '80px');
}
function draw(){
background(150,150,150);
drawGrid(25);
textSize(32);
text(nf(("(x-"))+(mx-height/2)+(")^2+(y-")+(my-width/2)+(")^2=")+(r^2),20,30);
text((nf(x1,3,0)+("=")+(r)+("cos(")+nf(a1/2,1,2)+(")")), 20, 60);
text((nf(y1,3,0)+("=")+(r)+("sin(")+nf(a1/2,1,2)+(")")), 20, 90);
text(("Area = ")+nf(PI*(r^2),4,1), 20, 120);
text(("Circumference = ")+nf(PI*(2*r),4,1), 20, 150);

fill(0,255,0);
ellipse (mx, my, r, r);
val = slider.value();
r = val;
if (a1<=360) {

a1+=.01;

}
else {
a1=0;
}

drawRight();


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
    if (mouseX < width & mouseY < height) {
    mx=mouseX;
    my=mouseY;
    mx-=mx%10;
    my-=my%10;
  }

}
}

function polygon(x, y, radius, npoints) {
  var angle = TWO_PI / npoints;
  beginShape();
  for (a = 0; a < TWO_PI; a += angle) {
    var sx = x + cos(a) * radius;
    var sy = y + sin(a) * radius;
    vertex(sx, sy);
  }
  endShape(CLOSE);
}
