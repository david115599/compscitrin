W=(1422.5)
H=(800)
var deltax=3;
var x=60;
var starx = [];
var stary = [];
var starw = [];
var sx = 0;
var sy = 0;
var sxv = 2;
var syv = 2;
var beginX = 20.0;  // Initial x-coordinate
var beginY = 10.0;  // Initial y-coordinate
var endX = 570.0;   // Final x-coordinate
var endY = 320.0;   // Final y-coordinate
var distX;          // X-axis distance to move
var distY;          // Y-axis distance to move
var exponent = 4;   // Determines the curve
var xs = 0.0;        // Current x-coordinate
var ys = 0.0;        // Current y-coordinate
var step = 0.01;    // Size of each step avar the path
var pct = 0.0;   


function setup() {

  createCanvas(W,H);
  background(0,0,180);
  for(var i = 0; i<50; i++){
    starx.push(random(0,width));
    stary.push(random(0,height));
    starw.push(random(1,6));
  }
  distX = endX - beginX;
  distY = endY - beginY;
}

function draw() {

//sun/moon
fill(0, 2);
  rect(0, 0, width, height);
  pct += step;
  if (pct < 1.0) {
    xs = beginX + (pct * distX);
    ys = beginY + (pow(pct, exponent) * distY);
  }
  fill(255);
  ellipse(xs, ys, 20, 20);
if (sx > -1 & sx < width) {
    sx = sx+sxv;
  }
  else{
    sxv = -sxv
    sx = sx+sxv;
  }
  if (sy > -1 & sy < 400) {
    sy = sy+syv;
  }
  else{
    syv = -syv
    sy = sy+syv;
  }
  fill(255,255,0);
ellipse(sx,sy,80,80)
//stars
for(var i = 0; i<50; i++){
    fill(255,0,0);
    ellipse(starx[i],stary[i],starw[i],starw[i]);
  }
//House
fill(100,255,100);
rect(0,500, W, H/2);
fill(0,0,180);
rect(W/2-W/4/2,H/2.5,W/4,H/2);
triangle(W/3,H/2.5,W/2, H/5, W/1.5, H/2.5);
fill(150,100,100);
rect(1422.5/2-75/2,570,75,150);
fill(255,255,255);
rect(580-15,500,75,75);
rect(800-15,500,75,75);
rect(580-15,400,75,75);
rect(800-15,400,75,75);
rect(580-15,600,75,75);
rect(800-15,600,75,75);
rect(690-15,400,75,75);
//Path
fill(255,0,0)
rect(690-15,720,10,10);
rect(690-15,740,10,10);
rect(690-15,760,10,10);
rect(690-15,780,10,10);
rect(700-15,730,10,10);
rect(700-15,750,10,10);
rect(700-15,770,10,10);
rect(700-15,790,10,10);
rect(710-15,720,10,10);
rect(710-15,740,10,10);
rect(710-15,760,10,10);
rect(710-15,780,10,10);
rect(720-15,730,10,10);
rect(720-15,750,10,10);
rect(720-15,770,10,10);
rect(720-15,790,10,10);
rect(730-15,720,10,10);
rect(730-15,740,10,10);
rect(730-15,760,10,10);
rect(730-15,780,10,10);
rect(740-15,730,10,10);
rect(740-15,750,10,10);
rect(740-15,770,10,10);
rect(740-15,790,10,10);
rect(750-15,720,10,10);
rect(750-15,740,10,10);
rect(750-15,760,10,10);
rect(750-15,780,10,10);
fill(255,150,0)
rect(690-15,730,10,10);
rect(690-15,750,10,10);
rect(690-15,770,10,10);
rect(690-15,790,10,10);
rect(700-15,720,10,10);
rect(700-15,740,10,10);
rect(700-15,760,10,10);
rect(700-15,780,10,10);
rect(710-15,730,10,10);
rect(710-15,750,10,10);
rect(710-15,770,10,10);
rect(710-15,790,10,10);
rect(720-15,720,10,10);
rect(720-15,740,10,10);
rect(720-15,760,10,10);
rect(720-15,780,10,10);
rect(730-15,730,10,10);
rect(730-15,750,10,10);
rect(730-15,770,10,10);
rect(730-15,790,10,10);
rect(740-15,720,10,10);
rect(740-15,740,10,10);
rect(740-15,760,10,10);
rect(740-15,780,10,10);
rect(750-15,730,10,10);
rect(750-15,750,10,10);
rect(750-15,770,10,10);
rect(750-15,790,10,10);
//Tree
fill(139,69,19);
rect(200,450,50,200);
fill(0,255,0);
ellipse(228,390,200,200);
}