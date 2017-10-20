W=(401)
H=(401)


function setup() {

  createCanvas(W,H);
  background(255,255,255);
}

function draw(){
Grid();
}
function mouseClicked() {
  ellipse(mouseX, mouseY, 40, 40);
  // prevent default
  return true;
}
function Grid(){
  for(var i = .5; i < W; i += 10) {
     strokeWeight(1);
     line(W, i,0,i );
     line(i, 0,i,W );
     strokeWeight(3);
     line(W/2, 0,W/2,W );
     line(H,H/2,0,H/2 );
   }
}
