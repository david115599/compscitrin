function setup(){
createCanvas (400,400);
drawGrid(25);
}
var x =();
var y =();
var a =();
var b =();

function draw(){

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
