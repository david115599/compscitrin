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
rotate(90);
}

function drawGrid2(size){

for (var i = 00; i < width; i += 50){
/*strokeWeight(5);
  line(0,i,width,i);
  line(i,0,i,height);*/
strokeWeight(1);
  line(i,400,(400),350-i);

  }

}
