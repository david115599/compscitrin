
var y = 0;
var x = 0;
var v = 10;
var q = 10;
//var bg = loadImage("House.png");

function setup() {


  createCanvas(600, 600);
}

function draw() {



  stroke(226, 204, 0);
  line(0, y, width, y);
  background(255)
  fill(0);
  ellipse(x+100, y+100, 80, 80);
  ellipse(x+150, y+150, 80, 80);
  ellipse(x+100, y+50, 80, 80);
  ellipse(x+200, y+100, 80, 80);
  ellipse(x+250, y+150, 80, 80);
  fill(255,0,0);
  ellipse(x+120, y+35, 5, 5);
  ellipse(x+80, y+35, 5, 5);
  ellipse(x+100, y+50, 20, 40);


if (y > -1 & y < height-200) {
    y = y+v+++v^2;
  }
else{
    v = -v
    y = y+v+++v^2;
  }
  if (x > -1 & x < width-200) {
    x = x+q+++q^2;
  }
else{
    q = -q
    x = x+q+++q^2;
  }
}


