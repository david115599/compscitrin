var r = (10);
var x = (200);
var y = (200);
var a = (0);
var b = (0);
var slider;
var val;
function setup(){
createCanvas (400,400);
slider = createSlider(0, 255, 100);
 slider.position(10, 10);
 slider.style('width', '80px');
}

function draw(){
drawGrid(25);
ellipse (x-a, y-b, r, r);
val = slider.value();
background(val);
}
function drawGrid(size){
for (var i = 0; i < width; i += size){
  line(0,i,width,i);
  line(i,0,i,height)
  }
  strokeWeight(3);
  line(width/2,0,width/2,height);
  line(0,height/2,width,height/2);
  strokeWeight(1);
}
