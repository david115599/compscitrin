var r = (10);
var x = (200);
var y = (200);
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
var a1=0; //angle
var x1;//triangle coordinate x
var y1;// triangle coordinate y
var mx;
var my;
function setup(){
createCanvas (500,500);
 slider = createSlider(0, 500, 500);
 slider.position(10, 10);
 slider.style('width', '80px');
}
function draw(){
background(150,150,150);
drawGrid(25);
fill(0,255,0);
ellipse ((mx)-a, (my)-b, r, r);
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

fill('blue');
triangle(0, 0, x1, y1,x1,0);
triangle(0,0,-x1,-y1,-x1,0);

}
function mouseReleased() {
  if(mouseReleased) {
    mx=mouseX;
    my=mouseY;
    mx-=mx%10;
    my-=my%10;
}
}
