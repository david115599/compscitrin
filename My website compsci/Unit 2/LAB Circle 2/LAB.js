var W=(400);
var H=(400);


function setup() {

  createCanvas(W,H);
  background(255);
  for(var i = 0; i < W; i += 10) {
     stroke(0,0,0);
    // line(0, i, W,H );
   }

drawGrid2(25);
translate(385,-5);
rotate(90);
drawGrid2(25);
}

function drawGrid2(size){

for (var i = -600; i < 5*width; i += 50){
/*strokeWeight(5);
  line(0,i,width,i);
  line(i,0,i,height);*/
strokeWeight(1);
  line(i,W+40,-i+(W*2)-1,-i);

  }

}
