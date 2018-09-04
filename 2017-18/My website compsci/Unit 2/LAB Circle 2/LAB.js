var W=(401);
var H=(401);


function setup() {

  createCanvas(W,H);
  background(255);
  for(var i = 0; i < W; i += 10) {
     stroke(0,0,0);
    // line(0, i, W,H );
   }

drawGrid2(25);

}

function drawGrid2(size){

for (var i = 00; i < width; i += 50){
strokeWeight(.5);
stroke(0,0,0);
  line(0,i,width,i);
  line(i,0,i,height);
strokeWeight(2);
stroke(0,0,255);
  line(i,400,400,400-i);
stroke(255,0,0);
  line(00,400-i,400-i,400);
stroke(255,255,0);
  line(0,i,400-i,0);
stroke(0,255,0);
  line(400,400-i,400-i,0);


  }

}
