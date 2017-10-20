<<<<<<< HEAD
W=(601)
H=(601)

=======
var W=(401);
var H=(401);
var a = (0);
var b = (0);
var x = (200);
var y = (200);
var r;
>>>>>>> 66d0b4a5dc4bfd842b9424430779aaf1bc6b77ba
function setup() {

  createCanvas(W,H);
  background(255,255,255);

  rSlider = createSlider(0, 50, 0, .009);
  rSlider.position(20, 20);
  var r=rSlider;
}

function draw(){
Grid();
<<<<<<< HEAD
ellipse(0, 0, r, r);
}

=======
ellipse(x,y,r,r);

}
function mouseClicked() {

  ellipse(mouseX, mouseY, 40, 40);
  // prevent default
  return true;
}
>>>>>>> 66d0b4a5dc4bfd842b9424430779aaf1bc6b77ba
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
