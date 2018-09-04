
var y = 0;
var x = 0;
var v = 1;
var q = 4;
var sgy = 0;
var sgx = 0;
var sgv = 1;
var sgq = 2;
var sby = 0;
var sbx = 0;
var sbv = 1;
var sbq = 2;
var sry = 0;
var srx = 0;
var srv = 2;
var srq = 2;
var spy = 0;
var spx = 0;
var spv = 2;
var spq = 2;
function setup() {


  createCanvas(800, 500);
}

function draw() {
//Moving Ball
stroke(0, 0, 0);
background(200)
fill(255,255,0);
ellipse(x+100, y+100, 20, 20);


if (y > -1 & y < height-100) {
  y = y+v;
}
else{
  v = -v
  y = y+v;
}
if (x > -1 & x < width-100) {
  x = x+q;
}
else{
  q = -q
  x = x+q;
}
  //Grean square
  stroke(0, 0, 0);
  fill(0,255,0);
  rect(sgx+200, sgy+0, 50, 50);


  if (sgy > -1 & sgy < height-150) {
    sgy = sgy+sgv;
  }
  else{
    sgv = -sgv
    sgy = sgy+sgv;
  }
  //Blue square
  stroke(0, 0, 0);
  fill(0,0,255);
  rect(sby, sbx+100, 50, 50);


  if (sby > -1 & sby < height+200) {
    sby = sby+sbv++;
  }
  else{
    sbv = 0;
    sby = 0;
}
 //Red square
  stroke(0, 0, 0);
  fill(255,0,0);
  rect(sry, 10, 50, 50);


  if (sry > -1 & sry < height+200) {
    sry = sry+srv;
  }
  else{
    srv = -srv
    sry = sry+srv;
  }
  //Pulsing Ball
stroke(0, 0, 0);
fill(0,0,0);
ellipse(700, 350, spx+5, spx+5);


if (spx > -1 & spx < 150) {
  spx = spx+spv;
}
else{
  spv = -spv
  spx = spx+spv;
}

}


