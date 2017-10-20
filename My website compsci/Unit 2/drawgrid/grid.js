W=(601)
H=(601)
var r;
function setup() {

  createCanvas(W,H);
  background(255,255,255);

  rSlider = createSlider(0, 50, 0, .009);
  rSlider.position(20, 20);
  r=rSlider;
}

function draw(){
Grid();
ellipse(200, 200, r, r);
}

function Grid(){
  for(var i = .5; i < W; i += 20) {
     strokeWeight(1);
     line(W, i,0,i );
     line(i, 0,i,W );
     strokeWeight(3);
     line(W/2, 0,W/2,W );
     line(H,H/2,0,H/2 );
   }
}
