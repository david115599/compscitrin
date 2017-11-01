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

for (var i = 00; i <= 200; i += 10){

stroke(255,0,0);
  line(400-i,400,400,200+i);
  line(0,400-i,200-i,400);
  line(0,i,200-i,0);
  line(400-i,0,400,200-i);
translate(200,200);
line(0,i,200-i,0);
translate(-200,-200);
translate(-200,200);
  line(400-i,0,400,200-i);
translate(200,-200);
translate(-200,-200);
  line(400-i,400,400,200+i);
translate(200,200);
translate(200,-200);
  line(0,400-i,200-i,400)
translate(-200,200);


  }

}
