var W=(800);
var H=(400);


function setup() {

  createCanvas(W,H);
  background(255);
  for(var i = 0; i < W; i += 10) {
     stroke(0,0,0);
    // line(0, i, W,H );
   }
drawGrid1(25);
rect(400,-1,400,401);
drawGrid2(25);
translate(564,-364);
rotate(90);
drawGrid2(25);
}
function drawGrid1(size){
for (var i = -600; i < 2*width; i += 100){
strokeWeight(5);
  line(0,i,width,i);
  line(i,0,i,height);
strokeWeight(1);
  line(i-400,400,i+400,0);
  line(i+400,400,i-400,0);
  line(0,i+50,width,i+50);
  line(i+50,0,i+50,height);

  }

}
function drawGrid2(size){
translate(400,0);
for (var i = -600; i < 2*width; i += 50){
/*strokeWeight(5);
  line(0,i,width,i);
  line(i,0,i,height);*/
strokeWeight(1);
  line(i,440,-i+799,-i);

  }

}
