W=(1422.5)
H=(800)


function setup() {

  createCanvas(W,H);
  background(100,255,100);

  fill(10,80,200);
  rect(0, 0, W, H/1.5);
  fill(0,0,180);
  rect(W/2-W/4/2,H/2.5,W/4,H/2);
  triangle(W/3,H/2.5,W/2, H/5, W/1.5, H/2.5);
  fill(150,100,100);
  rect(700,570,75,150);
  fill(255,255,255);
  rect(580,540,75,75);
  rect(800,450,75,75);
  rect(580,360,75,75);


}