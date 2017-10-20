<<<<<<< HEAD
var rSlider;
=======
var x =(200);
var y =(200);
var a =(0);
var b =(0);
var r=(20);

>>>>>>> 0e3e59d7935e2280479be709c3f7ad8907ac3ed1
function setup(){
createCanvas (400,400);
drawGrid(25);
// create sliders
  rSlider = createSlider(0, 50, 0, 100);
  rSlider.position(20, 20);
}

function draw(){
ellipse(x+a,y+b,r,r);
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
