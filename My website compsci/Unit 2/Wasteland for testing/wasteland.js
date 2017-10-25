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
function setup(){
createCanvas (1000,1000);
 slider = createSlider(0, width, 100);
 slider.position(10, 10);
 slider.style('width', '80px');
}
function draw(){
background(150,150,150);
drawGrid(25);
ellipse ((width/2)-a, (height/2)-b, r, r);
val = slider.value();
r = val;
drawRight(); //animated right triangle
translate(-width/2, -height/2);

}

function drawRight() {

theta = theta * speed;

x1 = r*25*cos(theta) ;
y1 = r*25*sin(theta) ;
//strokeWeight(2.5);

fill('blue');
triangle(0, 0, x1, y1,x1,0);
triangle(0,0,-x,-y,-x,0);

//dots
fill('red')
strokeWeight(1)
ellipse(0,0,r*.5,r*.5);//center


ellipse(x,y,r*.5,r*.5);//position on circle


ellipse(x,0,r*.5,r*.5);//x intercept

//ellipse(-x,-y,r*1.1,r*1.1);
//ellipse(-x,0,r*1.1,r*1.1);

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
