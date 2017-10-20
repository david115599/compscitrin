var W=(401);
var H=(401);
var a = (0);
var b = (0);
var x = (200);
var y = (200);
var r;
function setup() {

  createCanvas(W,H);
  background(255,255,255);
}

function draw(){
Grid();
ellipse(x,y,r,r);

}
function mouseClicked() {

  ellipse(mouseX, mouseY, 40, 40);
  // prevent default
  return true;
}
function Grid(){
  for(var i = .5; i < W; i += 10) {
     strokeWeight(1);
     stroke(0,255,0);
     line(W, i,0,i );
     line(i, 0,i,W );
     stroke(255,0,0);
     strokeWeight(3);
     line(W/2, 0,W/2,W );
     line(H,H/2,0,H/2 );
   }
}
