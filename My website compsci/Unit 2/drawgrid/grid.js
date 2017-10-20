var r = (10)
var x = (width/2);
var y = (height/2);
var a = (0);
var b = (0);
function setup(){
createCanvas (400,400);
drawGrid(25);
// create sliders
  rSlider = createSlider(0, 50, 0, .009);
  rSlider.position(20, 20);
}

function draw(){
ellipse (x-a, y-b, r, r)
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
