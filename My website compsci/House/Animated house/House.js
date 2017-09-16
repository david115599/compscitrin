W=(1422.5)
H=(800)


function setup() {

  createCanvas(W,H);
  background(100,255,100);

  fill(10,80,255);
  rect(0, 0, W, H/1.5);
  fill(0,0,255);
  rect(W/2-W/4/2,H/2.5,W/4,H/2);
  triangle(W/2,H/2.5,400, 20, 86, 75);

}