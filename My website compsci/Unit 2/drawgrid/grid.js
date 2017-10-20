<<<<<<< HEAD
var r = rSlider.value();
=======
var rSlider;
var x =(200);
var y =(200);
var b =(0);
var a =(0);
var r=20;
>>>>>>> e5c0421aaa60f527f36575520b38984d032008de
function setup(){
createCanvas (400,400);
drawGrid(25);
// create sliders
  rSlider = createSlider(0, 50, 0, .009);
  rSlider.position(20, 20);
}

function draw(){
<<<<<<< HEAD
ellipse (0, 0, r, r)
=======
ellipse(x+a,y+b,r,r);
>>>>>>> e5c0421aaa60f527f36575520b38984d032008de
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
