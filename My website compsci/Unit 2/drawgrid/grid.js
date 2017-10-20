<<<<<<< HEAD
var W=(401);
var H=(401);

=======
var W=(601)
var H=(601)
var r;
>>>>>>> 8b48a5a7f4a08ed444fb0775b6c8950061a20245
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
