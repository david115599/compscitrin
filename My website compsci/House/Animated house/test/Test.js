W=(400)
H=(400)


function setup() {

  createCanvas(W,H);
  background(100);

  fill(130,26,10);
  rect(0, 0, W, H);
  fill(90,60,50)
  line(0,0,W,H);
  line(W,0,0,H);
  rect(W/4, H/4, W/2, H/2);
  fill(0,100,100)
  ellipse(W/2, H/2, W/2, H/2);

}